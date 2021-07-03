package locations;

import org.modelmapper.ModelMapper;
//import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

//import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationsService {

    private ModelMapper modelMapper;


    private final List<Location> locations = Collections.synchronizedList(new ArrayList<>(
            List.of(
                    new Location(1001, "Pálffy terasz", 47.688312684390965, 17.63382643461122),
                    new Location(1002, "Sol Instituto", 36.719359297592376, -4.364970322146287)
            )));

    public LocationsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<LocationDto> getLocations() {
//        Type targetListType = new TypeToken<List<LocationDto>>(){}.getType();
//        return modelMapper.map(locations, targetListType);
        return locations.stream()
                .map(location -> modelMapper.map(location, LocationDto.class))
                .collect(Collectors.toList());
    }
}
