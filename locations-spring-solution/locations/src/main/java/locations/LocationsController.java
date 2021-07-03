package locations;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/locations")
public class LocationsController {

    private final LocationsService locationsService;

    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }

    @GetMapping
    public List<LocationDto> getLocations(@RequestParam Optional<String> prefix) {
        return locationsService.getLocations(prefix);
    }

    @GetMapping("/{id}")
    public LocationDto findLocationById(@PathVariable("id") long id) {
        return locationsService.findLocationById(id);
    }
}
