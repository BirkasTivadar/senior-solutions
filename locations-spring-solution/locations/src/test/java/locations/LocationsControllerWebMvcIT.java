package locations;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(controllers = LocationsController.class)  // Ha több controller van a csomagban, a Spring próbálja mindegyik Service-t meghívni
@WebMvcTest
public class LocationsControllerWebMvcIT {

    @MockBean
    LocationsService locationsService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testGetLocations() throws Exception {
        when(locationsService.getLocations(any()))
                .thenReturn(List.of(
                        new LocationDto(1, "Pálffy terasz", 47.688312684390965, 17.63382643461122),
                        new LocationDto(2, "Sol Instituto", 36.719359297592376, -4.364970322146287)
                ));

        mockMvc.perform(get("/api/locations"))
//                .andDo(print());
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", equalTo("Pálffy terasz")));
    }

    @Test
    void testFindLocationById() throws Exception {
        when(locationsService.findLocationById(any()))
                .thenReturn(new LocationDto(1, "Pálffy terasz", 47.688312684390965, 17.63382643461122));

        mockMvc.perform(get("/api/locations/"))
                .andExpect(status().isOk())
                .equals(new LocationDto(1, "Pálffy terasz", 47.688312684390965, 17.63382643461122));
    }

    @Test
    void testCreateLocation() throws Exception {
        when(locationsService.createLocation(any()))
                .thenReturn(new LocationDto(1, "Pálffy terasz", 47.688312684390965, 17.63382643461122));

        mockMvc.perform(get("/api/locations/"))
                .andExpect(status().isOk())
                .equals(new LocationDto(1, "Pálffy terasz", 47.688312684390965, 17.63382643461122));
    }

    @Test
    void testUpdateLocation() throws Exception {
        when(locationsService.createLocation(any()))
                .thenReturn(new LocationDto(1, "Pálffy terasz", 47.688312684390965, 17.63382643461122));

        mockMvc.perform(get("/api/locations/"))
                .andExpect(status().isOk())
                .equals(new LocationDto(1, "Pálffy terasz", 47.688312684390965, 17.63382643461122));
    }


}
