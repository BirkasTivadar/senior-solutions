package locationsgradle;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationsGradleController {

    private LocationsGradleService locationsGradleService;

    public LocationsGradleController(LocationsGradleService locationsGradleService) {
        this.locationsGradleService = locationsGradleService;
    }

    @GetMapping("/locations")
    public List<LocationGradle> getLocations() {
        return locationsGradleService.getLocations();
//        StringBuilder sb = new StringBuilder();
//        locationsService.getLocations().forEach(
//                e -> sb.append(e)
//                        .append(" ")
//        );
//        return sb.toString();
    }
}
