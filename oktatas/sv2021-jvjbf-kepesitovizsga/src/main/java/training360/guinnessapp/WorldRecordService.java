package training360.guinnessapp;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import training360.guinnessapp.dto.WorldRecordCreateCommand;
import training360.guinnessapp.dto.WorldRecordDto;

@Service
@AllArgsConstructor
public class WorldRecordService {

    private ModelMapper modelMapper;

    private WorldRecordRepository repository;

    private RecorderRepository recorderRepository;

    public WorldRecordDto createWorldRecord(WorldRecordCreateCommand command) {
        Recorder recorder = recorderRepository.findById(command.getRecorderId()).orElseThrow(() -> new IllegalArgumentException("not found"));
        WorldRecord worldRecord = new WorldRecord(
                command.getDescription(),
                command.getValue(),
                command.getUnitOfMeasure(),
                command.getDateOfRecord(),
                recorder);
        repository.save(worldRecord);
        return modelMapper.map(worldRecord, WorldRecordDto.class);
    }
}
