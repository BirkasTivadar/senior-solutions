package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DistanceServiceTest {

    @Mock
    LocationRepository locationRepository;

    @InjectMocks
    DistanceService distanceService;

    @Test
    void testCalculateDistanceWithEmptyList() {
        Optional<Double> distance = distanceService.calculateDistance("Bazilika", "Escuelalaplaya");

        assertEquals(Optional.empty(), distance);
    }

    @Test
    void testCalculateDistance() {
        when(locationRepository.findByName("Győr"))
                .thenReturn(Optional.of(new Location("Győr", 47.6660111, 17.6439626)));

        when(locationRepository.findByName("Győrújbarát"))
                .thenReturn(Optional.of(new Location("Győrújbarát", 47.6076284, 17.6389745)));

        Optional<Double> optionalDistance = distanceService.calculateDistance("Győr", "Győrújbarát");

        assertEquals(6.503, optionalDistance.get(), 0.01);
    }

}