package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocationsControllerRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @Autowired
    LocationsService locationsService;

    @BeforeEach
    void init() {
        locationsService.deleteAllLocations();
    }


    @Test
    void testGetLocations() {
        LocationDto locationDto =
                template.postForObject("/api/locations", new CreateLocationCommand("Pálffy terasz", 47.688312684390965, 17.63382643461122), LocationDto.class);

        assertEquals("Pálffy terasz", locationDto.getName());

        template.postForObject("/api/locations", new CreateLocationCommand("Sol Instituto", 36.719359297592376, -4.364970322146287), LocationDto.class);

        List<LocationDto> locations =
                template.exchange("/api/locations",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<LocationDto>>() {
                        })
                        .getBody();

        assertThat(locations)
                .extracting(LocationDto::getName)
                .containsExactly("Pálffy terasz", "Sol Instituto");
    }

}
