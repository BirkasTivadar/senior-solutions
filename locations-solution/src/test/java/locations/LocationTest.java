package locations;

import org.junit.jupiter.api.*;

import java.util.Objects;

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
    void testFilterOnNorthWithRepeatedTest(RepetitionInfo repetitionInfo) {
        int index = repetitionInfo.getCurrentRepetition() - 1;

        assertEquals(values[index][0], Location.class.cast(values[index][1]).isOnEquator());
    }
}