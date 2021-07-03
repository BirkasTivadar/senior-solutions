package locations;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LocationsServiceTest {

    private final LocationsService locationsService = new LocationsService(new ModelMapper());

    List<LocationDto> locations = locationsService.getLocations(Optional.empty());

    @Test
    void testGetLocations() {
        assertEquals(2, locations.size());

        assertEquals("Sol Instituto", locations.get(1).getName());
    }

}