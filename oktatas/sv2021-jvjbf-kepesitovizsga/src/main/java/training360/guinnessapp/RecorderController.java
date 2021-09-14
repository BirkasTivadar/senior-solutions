package training360.guinnessapp;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import training360.guinnessapp.dto.RecorderCreateCommand;
import training360.guinnessapp.dto.RecorderDto;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/recorders")
public class RecorderController {

    private final RecorderService recorderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecorderDto createRecorder(@Valid @RequestBody RecorderCreateCommand command) {
        return recorderService.createRecorder(command);
    }
}
