package locations;

public class LocationParser {

    public Location parse(String text) {
        String[] attributesOfLocation = text.split(",");

        String name = attributesOfLocation[0].trim();
        double lat = Double.parseDouble(attributesOfLocation[1].trim());
        double lon = Double.parseDouble(attributesOfLocation[2].trim());

        return new Location(name, lat, lon);
    }
}
