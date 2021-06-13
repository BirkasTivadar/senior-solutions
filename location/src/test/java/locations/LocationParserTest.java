package locations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationParserTest {

    @Test
    @DisplayName("Test parsing datas of location separate with comma")
    void testParse() {
        String text = "Budapest,47.497912,19.040235";

        Location location = new LocationParser().parse(text);

        assertEquals("Budapest", location.getName());
        assertEquals(47.497912, location.getLat());
        assertEquals(19.040235, location.getLon());
    }
}