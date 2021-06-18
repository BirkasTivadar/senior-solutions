package locations;

import java.util.Optional;

public class DistanceService {

    private LocationRepository locationRepository;

    public DistanceService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Optional<Double> calculateDistance(String name1, String name2) {

        Optional<Location> optionalLocation1 = locationRepository.findByName(name1);
        Optional<Location> optionalLocation2 = locationRepository.findByName(name2);

        if (optionalLocation1.isPresent() && optionalLocation2.isPresent()) {
            return Optional.of(optionalLocation1.get().distance(optionalLocation2.get()));
        } else {
            return Optional.empty();
        }

    }
}
