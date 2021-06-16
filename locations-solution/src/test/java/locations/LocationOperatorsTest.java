package locations;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@LocationOperationsFeatureTest
class LocationOperatorsTest {

    LocationOperators locationOperators = new LocationOperators();

    LocationParser locationParser = new LocationParser();

    List<Location> locations = Arrays.asList(
            locationParser.parse("Escuelalaplaya,36.7225263,-4.3231241"),
            new Location("Teatro Colón", -34.59898815321077, -58.38250415283132),
            new Location("Plaza Mayor De San Francisco", -16.49492693840138, -68.13694418833528),
            new Location("Bazilika", 47.5007388, 19.0532146),
            locationParser.parse("rastro.website,38.85501,0")
    );

    List<Location> filtersOnNorth = locationOperators.filterOnNorth(locations);

    @Test
    void testFilterOnNorth() {
        assertEquals(3, filtersOnNorth.size());
        assertEquals("Escuelalaplaya", filtersOnNorth.get(0).getName());
        assertEquals("Bazilika", filtersOnNorth.get(1).getName());
        assertEquals("rastro.website", filtersOnNorth.get(2).getName());
    }

    private String[] values = {"Escuelalaplaya", "Bazilika", "rastro.website"};

    @RepeatedTest(value = 3)
    void testFilterOnNorthWithRepeatedTest(RepetitionInfo repetitionInfo) {
        int index = repetitionInfo.getCurrentRepetition() - 1;

        assertEquals(values[index], filtersOnNorth.get(index).getName());
    }
}