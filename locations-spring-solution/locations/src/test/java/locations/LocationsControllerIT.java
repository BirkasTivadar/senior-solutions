package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LocationsControllerIT {

    @Autowired
    LocationsController locationsController;

    @Test
    void testGetLocations() {
        List<LocationDto> locations = locationsController.getLocations(Optional.empty());

        assertThat(locations.size()).isEqualTo(2);

        assertThat(locations.get(0).getName())
                .startsWith("Pálffy");

        assertThat(locations.get(1).getLon()).isEqualTo(-4.364970322146287);

    }

}