package locations;

import org.modelmapper.ModelMapper;
//import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

//import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class LocationsService {

    private ModelMapper modelMapper;

    private AtomicLong idGenerator = new AtomicLong();

    private final List<Location> locations = Collections.synchronizedList(new ArrayList<>(
            List.of(
                    new Location(idGenerator.incrementAndGet(), "Pálffy terasz", 47.688312684390965, 17.63382643461122),
                    new Location(idGenerator.incrementAndGet(), "Sol Instituto", 36.719359297592376, -4.364970322146287)
            )));

    public LocationsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<LocationDto> getLocations(Optional<String> prefix) {
//        Type targetListType = new TypeToken<List<LocationDto>>(){}.getType();
//        List<Location> filtered = locations.stream().
//                filter(location -> prefix.isEmpty() || location.getName().toLowerCase().startsWith(prefix.get().toLowerCase())).collect(Collectors.toList());
//        return modelMapper.map(filtered, targetListType);
        return locations.stream().
                filter(location -> prefix.isEmpty() || location.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
                .map(location -> modelMapper.map(location, LocationDto.class))
                .collect(Collectors.toList());
    }

    private Location getLocationById(long id) {
        return locations.stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .orElseThrow(() -> new LocationNotFoundException("Location not found: " + id));
    }

    public LocationDto findLocationById(Long id) {
        return modelMapper.map(getLocationById(id),
//                locations.stream()
//                        .filter(l -> l.getId() == id).findAny()
//                        .orElseThrow(() -> new LocationNotFoundException("Location not found: " + id)),
                LocationDto.class);
    }

    public LocationDto createLocation(CreateLocationCommand command) {
        Location location = new Location(idGenerator.incrementAndGet(), command.getName(), command.getLat(), command.getLon());
        locations.add(location);
        return modelMapper.map(location, LocationDto.class);
    }

    public LocationDto updateLocation(long id, UpdateLocationCommand command) {
        Location location = getLocationById(id);
        if (command.getName().isPresent()) {
            location.setName(command.getName().get());
        }
        if (command.getLat().isPresent()) {
            location.setLat(command.getLat().get());
        }
        if (command.getLon().isPresent()) {
            location.setLon(command.getLon().get());
        }

        return modelMapper.map(location, LocationDto.class);
    }

    public void deleteLocation(long id) {
        Location location = getLocationById(id);
        locations.remove(location);
    }


    public void deleteAllLocations() {
        idGenerator = new AtomicLong();
        locations.clear();
    }
}
