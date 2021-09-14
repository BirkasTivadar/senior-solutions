package training360.guinnessapp;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import training360.guinnessapp.dto.RecorderCreateCommand;
import training360.guinnessapp.dto.RecorderDto;

@Service
@AllArgsConstructor
public class RecorderService {

    private ModelMapper modelMapper;

    private RecorderRepository repository;

    public RecorderDto createRecorder(RecorderCreateCommand command) {
        Recorder recorder = new Recorder(command.getName(), command.getDateOfBirth());
        repository.save(recorder);
        return modelMapper.map(recorder, RecorderDto.class);
    }
}
