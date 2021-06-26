package locations;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationsController {

    private List<Location> locations = List.of(
            new Location(1001, "Pálffy terasz", 47.688312684390965, 17.63382643461122),
            new Location(1002, "Sol Instituto", 36.719359297592376, -4.364970322146287)
    );

    @GetMapping("/locations")
    public String getLocations(){
        StringBuilder sb = new StringBuilder();
        locations.forEach(
                e-> sb.append(e)
                .append(" ")
        );
        return sb.toString();
    }
}
