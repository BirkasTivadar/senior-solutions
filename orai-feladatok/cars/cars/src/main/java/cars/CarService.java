package cars;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarService {

    private List<Car> cars = List.of(
            new Car("Opel", "Vectra", 11, CarCondition.NORMAL,
                    List.of(
                            new KmState(LocalDate.of(2015, 2, 3), 235),
                            new KmState(LocalDate.of(2017, 12, 31), 4563)
                    )),
            new Car("Toyota", "Starlet", 26, CarCondition.BAD,
                    List.of(
                            new KmState(LocalDate.of(1995, 6, 22), 1111),
                            new KmState(LocalDate.of(2016, 10, 23), 2345)
                    ))
    );


    public List<Car> getListCars() {
        return new ArrayList<>(cars);
    }

    public Set<String> getTypes() {
        return cars.stream().map(c -> c.getCarBrand()).collect(Collectors.toSet());
    }
}
