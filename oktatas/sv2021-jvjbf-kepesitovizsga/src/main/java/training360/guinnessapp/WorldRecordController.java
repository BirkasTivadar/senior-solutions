package training360.guinnessapp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import training360.guinnessapp.dto.BeatWorldRecordCommand;
import training360.guinnessapp.dto.BeatWorldRecordDto;
import training360.guinnessapp.dto.WorldRecordCreateCommand;
import training360.guinnessapp.dto.WorldRecordDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/worldrecords")
public class WorldRecordController {

    private final WorldRecordService worldRecordService;

    public WorldRecordController(WorldRecordService worldRecordService) {
        this.worldRecordService = worldRecordService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorldRecordDto createWorldRecord(@Valid @RequestBody WorldRecordCreateCommand command) {
        return worldRecordService.createWorldRecord(command);
    }

//    @PutMapping("{id}/beatrecords")
//    public BeatWorldRecordDto beatWorldRecord(@PathVariable("id") Long id, BeatWorldRecordCommand command){
//        return worldRecordService.beatWorldRecord(command);
//    }

}
