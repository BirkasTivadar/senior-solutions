package org.training360.musicstore;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/instruments")
public class MusicStoreController {

    private final MusicStoreService musicStoreService;

    public MusicStoreController(MusicStoreService musicStoreService) {
        this.musicStoreService = musicStoreService;
    }

    @GetMapping
    public List<InstrumentDto> getInstruments(@RequestParam Optional<String> brand, @RequestParam Optional<Integer> price) {
        return musicStoreService.getInstruments(brand, price);
    }

    @GetMapping("{id")
    public ResponseEntity findLocationById(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(musicStoreService.getInstrumentById(id));

        } catch (IllegalArgumentException iae) {
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping("/{id}")
//    public InstrumentDto getInstrumentById(@PathVariable("id") Long id) {
//        return musicStoreService.getInstrumentById(id);

//

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InstrumentDto addNewInstrument(@Valid @RequestBody CreateInstrumentCommand command) {
        return musicStoreService.addNewInstrument(command);
    }

    @PutMapping("{id}")
    public InstrumentDto updateInstrumentPrice(@PathVariable("id") Long id, @Valid @RequestBody UpdatePriceCommand command) {
        return musicStoreService.updateInstrumentPrice(id, command);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInstrumentById(@PathVariable("id") Long id) {
        musicStoreService.deleteInstrumentById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllInstruments() {
        musicStoreService.deleteAllInstruments();
    }
}
