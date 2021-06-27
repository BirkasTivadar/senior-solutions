package locations;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationsController {

    private final LocationsService locationsService;

    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }

    @GetMapping("/locations")
    public List<Location> getLocations() {
        return locationsService.getLocations();
//        StringBuilder sb = new StringBuilder();
//        locationsService.getLocations().forEach(
//                e -> sb.append(e)
//                        .append(" ")
//        );
//        return sb.toString();
    }
}
