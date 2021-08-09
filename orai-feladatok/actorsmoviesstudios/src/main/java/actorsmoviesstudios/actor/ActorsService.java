package actorsmoviesstudios.actor;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ActorsService {

    private ModelMapper modelMapper;

    private ActorsRepository repository;

    public List<ActorDto> getActors() {
        return repository.findAll().stream()
                .map(a -> modelMapper.map(a, ActorDto.class))
                .toList();
    }

    public ActorDto createActor(CreateActorCommand command) {
        Actor actor = new Actor(command.getName(), command.getBirthOfDate());
        repository.save(actor);
        return modelMapper.map(actor, ActorDto.class);
    }
}
