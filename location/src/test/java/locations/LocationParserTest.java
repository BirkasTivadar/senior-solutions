package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationParserTest {

    LocationParser locationParser;

    @BeforeEach
    void init() {
        locationParser = new LocationParser();
    }

    @Test
    @DisplayName("Test parsing datas of location separate with comma")
    void testParse() {
        String text = "Budapest,47.497912,19.040235";

        Location location = new LocationParser().parse(text);

        assertEquals("Budapest", location.getName());
        assertEquals(47.497912, location.getLat());
        assertEquals(19.040235, location.getLon());
    }

    @Test
    void testSame() {
        String text = "Budapest,47.497912,19.040235";
        Location location1 = locationParser.parse(text);
        Location location2 = locationParser.parse(text);

        assertEquals(location1.getName(), location2.getName());
        assertNotEquals(location1, location2);
    }

    @Test
    void testParseAll(){
        Location location = locationParser.parse("Budapest,47.497912,19.040235");

        assertAll(
                ()-> assertEquals("Budapest", location.getName()),
                ()-> assertEquals(47.497912, location.getLat()),
                ()-> assertEquals(19.040235, location.getLon())
        );
    }
}