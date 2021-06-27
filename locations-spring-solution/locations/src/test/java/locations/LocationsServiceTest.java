package locations;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocationsServiceTest {

    private final LocationsService locationsService = new LocationsService();

    List<Location> locations = locationsService.getLocations();

    @Test
    void testGetLocations() {
        assertEquals(2, locations.size());

        assertEquals("Sol Instituto", locations.get(1).getName());

    }

}