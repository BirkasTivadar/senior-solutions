package training360.guinnessapp;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import training360.guinnessapp.dto.RecorderCreateCommand;
import training360.guinnessapp.dto.RecorderDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/recorders")
public class RecorderController {

    private final RecorderService recorderService;

    public RecorderController(RecorderService recorderService) {
        this.recorderService = recorderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecorderDto createRecorder(@Valid @RequestBody RecorderCreateCommand command){
        return recorderService.createRecorder(command);
    }

}
