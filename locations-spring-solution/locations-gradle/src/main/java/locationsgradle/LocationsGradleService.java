package locationsgradle;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationsGradleService {

    private List<LocationGradle> locations = List.of(
            new LocationGradle(1001, "Pálffy terasz", 47.688312684390965, 17.63382643461122),
            new LocationGradle(1002, "Sol Instituto", 36.719359297592376, -4.364970322146287)
    );

    public List<LocationGradle> getLocations() {
        return locations;
    }
}
