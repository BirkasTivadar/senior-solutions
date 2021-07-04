package org.training360.musicstore;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/instruments")
public class MusicStoreController {

    private final MusicStoreService musicStoreService;

    public MusicStoreController(MusicStoreService musicStoreService) {
        this.musicStoreService = musicStoreService;
    }

    @GetMapping
    public List<InstrumentDTO> getInstruments(@RequestParam Optional<String> brand, @RequestParam Optional<Integer> price) {
        return musicStoreService.getInstruments(brand, price);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity findLocationById(@PathVariable("id") long id) {
//        try {
//            return ResponseEntity.ok(musicStoreService.getInstrumentById(id));
//
//        } catch (IllegalArgumentException iae) {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public InstrumentDTO getInstrumentById(@PathVariable("id") Long id) {
        return musicStoreService.getInstrumentById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InstrumentDTO addNewInstrument(@Valid @RequestBody CreateInstrumentCommand command) {
        return musicStoreService.addNewInstrument(command);
    }

    @PutMapping("{id}")
    public InstrumentDTO updateInstrumentPrice(@PathVariable("id") Long id, @Valid @RequestBody UpdatePriceCommand command) {
        return musicStoreService.updateInstrumentPrice(id, command);
    }

    @DeleteMapping("{id}")
    public void deleteInstrumentById(@PathVariable("id") Long id) {
        musicStoreService.deleteInstrumentById(id);
    }

    @DeleteMapping
    public void deleteAllInstruments() {
        musicStoreService.deleteAllInstruments();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Problem> handleNotFound(IllegalArgumentException exception) {
        Problem problem = Problem.builder()
                .withType(URI.create("instruments/not-found"))
                .withTitle("Not Found")
                .withStatus(Status.NOT_FOUND)
                .withDetail(exception.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);

    }
}
