package microservices.training.bikes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BikeController {

    private final BikeService bikeService;

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @GetMapping("/history")
    public List<Bike> getBikes() {
        return bikeService.getBikes();
    }

    @GetMapping("/users")
    public List<String> usersIdList() {
        return bikeService.getUsersId();
    }
}
