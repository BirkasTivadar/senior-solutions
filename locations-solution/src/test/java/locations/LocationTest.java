package locations;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LocationTest implements PrintNameCapable {

    LocationParser locationParser;

    @BeforeEach
    public void init() {
        locationParser = new LocationParser();
    }

    @Test
    void is_On_Equator() {
        String onEquator = "Panadería El Paraiso,0,-78.45011";
        Location panaderiaElParaiso = locationParser.parse(onEquator);
        assertTrue(panaderiaElParaiso.isOnEquator());
        assertFalse(panaderiaElParaiso.isOnPrimeMeridian());

    }

    @Test
    void is_On_Prime_Meridian() {
        String onPrimeMeridian = "rastro.website,38.85501,0";
        Location rastroWebsite = locationParser.parse(onPrimeMeridian);
        assertTrue(rastroWebsite.isOnPrimeMeridian());
        assertFalse(rastroWebsite.isOnEquator());
    }

    @Test
    void distance() {
        String gpsDeLaCasaMia = "Győr Cuha 41.,47.6660111,17.6439626";
        Location casaMia = locationParser.parse(gpsDeLaCasaMia);
        String gpsDeLaEscuelalaplaya = "Escuelalaplaya,36.7225263,-4.3231241";
        Location escuelaDeRomanNavarro = locationParser.parse(gpsDeLaEscuelalaplaya);

        assertEquals(2168, casaMia.distance(escuelaDeRomanNavarro), 2.1);
    }

    @Test
    void createLocationWithWrongLat() {
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,
                () -> new Location("Győr", 95.21, 102.45));
        assertEquals("Wrong lat: 95.21", iae.getMessage());

        IllegalArgumentException iae2 = assertThrows(IllegalArgumentException.class,
                () -> locationParser.parse("Győr, -95.21, 102.45"));
        assertEquals("Wrong lat: -95.21", iae2.getMessage());
    }

    @Test
    void createLocationWithWrongLon() {
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,
                () -> new Location("Győr", 45.21, 182.45));
        assertEquals("Wrong lon: 182.45", iae.getMessage());

        IllegalArgumentException iae2 = assertThrows(IllegalArgumentException.class,
                () -> new Location("Győr", 45.21, -182.45));
        assertEquals("Wrong lon: -182.45", iae2.getMessage());
    }


    Object[][] values = {
            {true, new Location("Panadería", 0, -78.45011)},
            {false, new Location("Győr", 45.6, -78.45011)},
            {true, new Location("Cuba", 0, 34.567)}
    };

    @RepeatedTest(value = 3)
    void testIsOnEquator(RepetitionInfo repetitionInfo) {
        int index = repetitionInfo.getCurrentRepetition() - 1;

        assertEquals(values[index][0], Location.class.cast(values[index][1]).isOnEquator());
    }


    @ParameterizedTest()
    @MethodSource("createIsOnPrimeMeridian")
    void testIsOnPrimeMeridian(boolean isOnPrimeMeridian, Location location) {

        assertEquals(isOnPrimeMeridian, location.isOnPrimeMeridian());
    }

    static Stream<Arguments> createIsOnPrimeMeridian() {
        return Stream.of(
                Arguments.arguments(true, new Location("rastro.website",38.85501,0)),
                Arguments.arguments(false, new Location("Győr",18.85501,45.567)),
                Arguments.arguments(false, new Location("Ecuador",39.89,23.456)),
                Arguments.arguments(true, new Location("Ecuador",89.89,0))
        );
    }


    @ParameterizedTest()
    @CsvSource(
            "47.6660111, 17.6439626,47.6076284, 17.6389745, 6.503"
    )
    void testDistanceFromCsv(double lat1, double lon1, double lat2, double lon2, double distance ){
        Location location1 = new Location("Győr", lat1, lon1);
        Location location2 = new Location("Győrújbarát", lat2, lon2);

        assertEquals(distance, location1.distance(location2), 0.001);
    }
}