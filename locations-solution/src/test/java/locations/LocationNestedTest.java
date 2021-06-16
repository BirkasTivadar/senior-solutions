package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocationNestedTest {

    LocationParser locationParser;

    @BeforeEach
    void init() {
        locationParser = new LocationParser();
    }

    @Nested
    class IsOnEquatorOrOnPrimeMeridianTrue {
        Location location;

        @BeforeEach
        void init() {
            location = locationParser.parse("Málaga,0,0");
        }

        @Test
        void testIsOnEquator() {
            assertTrue(location.isOnEquator());
        }

        @Test
        void testIsOnPrimeMeridian() {
            assertTrue(location.isOnPrimeMeridian());
        }
    }

    @Nested
    class IsOnEquatorOrOnPrimeMeridianFalse {
        Location location;

        @BeforeEach
        void init() {
            location = locationParser.parse("Győrújbarát,47.497912,19.040235");
        }

        @Test
        void testIsOnEquator() {
            assertFalse(location.isOnEquator());
        }

        @Test
        void testIsOnPrimeMeridian() {
            assertFalse(location.isOnPrimeMeridian());
        }
    }
}
