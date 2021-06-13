package locations;

public class LocationParser {

    public Location parse(String text) {
        String[] attributesOfLocation = text.split(",");

        String name = attributesOfLocation[0];
        double lat = Double.parseDouble(attributesOfLocation[1]);
        double lon = Double.parseDouble(attributesOfLocation[2]);

        return new Location(name, lat, lon);
    }
}
