package locations;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LocationsController {

    private List<Location> locations = List.of(
            new Location(1001, "Pálffy terasz", 47.688312684390965, 17.63382643461122),
            new Location(1002, "Sol Instituto", 36.719359297592376, -4.364970322146287)
    );

    @GetMapping("/")
    @ResponseBody
    public String getLocations() {
        StringBuilder sb = new StringBuilder();
        locations.forEach(
                e -> sb.append(e).append(" "));
//                e -> sb.append(e.getName()).append(" ")
//                        .append(e.getLat()).append(" ")
//                        .append(e.getLon())
//                        .append(" | "));
        return sb.append(LocalDateTime.now()).toString();
    }
}
