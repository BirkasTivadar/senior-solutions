package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class LocationsControllerTest {

    @Mock
    LocationsService locationsService;

    @InjectMocks
    LocationsController locationsController;

    @Test
    void testGetLocations() {

        ModelMapper modelMapper = new ModelMapper();

        List<Location> locations = Collections.synchronizedList(new ArrayList<>(
                List.of(new Location(1001, "Pálffy terasz", 47.688312684390965, 17.63382643461122))
        ));

        List<LocationDto> locationDtos = locations.stream()
                .map(location -> modelMapper.map(location, LocationDto.class))
                .collect(Collectors.toList());

        when(locationsService.getLocations(Optional.empty())).thenReturn(locationDtos);
        assertThat(locationsController.getLocations(Optional.empty())
                .get(0)
                .getName())
                .isEqualTo("Pálffy terasz");
    }

}