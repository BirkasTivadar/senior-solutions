package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class LocationsControllerTest {

    @Mock
    LocationsService locationsService;

    @InjectMocks
    LocationsController locationsController;

    @Test
    void testGetLocations(){
        when(locationsService.getLocations()).thenReturn(List.of(new Location(1001, "Pálffy terasz", 47.688312684390965, 17.63382643461122)));
        assertThat(locationsController.getLocations()
                .get(0)
                .getName())
                .isEqualTo("Pálffy terasz");
    }

}