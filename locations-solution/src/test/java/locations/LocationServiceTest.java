package locations;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class LocationServiceTest {

    LocationService locationService = new LocationService();

    LocationParser locationParser = new LocationParser();

    List<Location> locationsOriginal = Arrays.asList(
            locationParser.parse("Escuelalaplaya,36.7225263,-4.3231241"),
            new Location("Teatro Colón", -34.59898815321077, -58.38250415283132),
            new Location("Plaza Mayor De San Francisco", -16.49492693840138, -68.13694418833528),
            new Location("Bazilika", 47.5007388, 19.0532146),
            locationParser.parse("rastro.website,38.85501,0")
    );

    @TempDir
    Path tempDir;

    @RepeatedTest(value = 5)
    void testWriteLocations(RepetitionInfo repetitionInfo) throws IOException {

        Path file = tempDir.resolve("locations.csv");

        locationService.writeLocations(file, locationsOriginal);

        List<String> locationsString = Files.readAllLines(file);
        List<Location> locationsTest = locationsString.stream().map(ls -> locationParser.parse(ls)).collect(Collectors.toList());

        int index = repetitionInfo.getCurrentRepetition() - 1;

        assertEquals(5, locationsTest.size());
        assertEquals(locationsTest.get(index).getName(), locationsOriginal.get(index).getName());
        assertEquals(locationsTest.get(index).getLat(), locationsOriginal.get(index).getLat());
        assertEquals(locationsTest.get(index).getName(), locationsOriginal.get(index).getName());
    }

}