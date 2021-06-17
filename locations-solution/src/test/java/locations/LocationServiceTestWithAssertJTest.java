package locations;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class LocationServiceTestWithAssertJTest {


    LocationService locationService;

    LocationParser locationParser = new LocationParser();

    List<Location> locationsOriginal = Arrays.asList(
            locationParser.parse("Escuelalaplaya,36.7225263,-4.3231241"),
            new Location("Teatro Colón", -34.59898815321077, -58.38250415283132),
            new Location("Plaza Mayor De San Francisco", -16.49492693840138, -68.13694418833528),
            new Location("Bazilika", 47.5007388, 19.0532146),
            locationParser.parse("rastro.website,38.85501,0")
    );


    List<Location> locations;

    @BeforeEach
    void init() {
        locationService = new LocationService();

        Path path = Path.of("src/main/resources/results.csv");

        locationService.writeLocations(path, locationsOriginal);

        locations = locationService.readFromCsv(path);
    }

    @Test
    void testListOfLocations() {
        Location location = locations.get(0);

        assertThat(location.getName()).isEqualTo("Escuelalaplaya");
        assertThat(location.getName())
                .startsWith("Escuela")
                .endsWith("playa");

        assertThat(locations)
                .hasSize(5)
                .extracting(Location::getName).contains("Bazilika");

        assertThat(locations)
                .hasSize(5)
                .extracting(Location::getName, Location::getLat, Location::getLon)
                .contains(tuple("Plaza Mayor De San Francisco", -16.49492693840138, -68.13694418833528));
    }


    @Test
    void testHasZeroCoordinate() {
        Location location = locations.get(4);

        Condition<Location> hasZeroCoordinate =
                new Condition<>(l -> l.getLat() == 0 || l.getLon() == 0, "location has coordinate with zero");

        assertThat(location).has(hasZeroCoordinate);
    }
}
