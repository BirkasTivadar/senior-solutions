package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LocationTest {

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
}