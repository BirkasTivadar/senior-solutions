package actorsmoviesstudios.actor;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/actors/")
public class ActorsController {

    private ActorsService actorsService;

    public ActorsController(ActorsService actorsService) {
        this.actorsService = actorsService;
    }

    @GetMapping
    @Operation(summary = "Query all actors")
    public List<ActorDto> getActorsService(){
        return actorsService.getActors();
    }

    @PostMapping
    @Operation(summary = "Create an actor")
    @ResponseStatus(HttpStatus.CREATED)
    public ActorDto createActor(@RequestBody CreateActorCommand command){
        return actorsService.createActor(command);
    }
}
