package microservices.training.bikes;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BikeService {

    public static final String SEPARATOR = ";";

    private List<Bike> bikes = new ArrayList<>();


    public List<Bike> getBikes() {
        if (bikes.isEmpty()) {
            loadBikes();
        }
        return new ArrayList<>(bikes);
    }

    private void loadBikes() {

//        try (BufferedReader br = Files.newBufferedReader(Path.of("bikes.csv"))) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(BikeService.class.getResourceAsStream("bikes.csv")))) {
            String line;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            while ((line = br.readLine()) != null) {
                String[] lineArr = line.split(SEPARATOR);
                String bikeId = lineArr[0];
                String lastUserId = lineArr[1];
                LocalDateTime lastReturnTime = LocalDateTime.parse(lineArr[2], formatter);
                Double km = Double.parseDouble(lineArr[3]);
                Bike bike = new Bike(bikeId, lastUserId, lastReturnTime, km);
                bikes.add(bike);
            }

        } catch (IOException e) {
            throw new IllegalStateException("Can not read file", e);
        }
    }

    public List<String> getUsersId() {
        return getBikes().stream().map(Bike::getLastUserId).collect(Collectors.toList());
    }

}
