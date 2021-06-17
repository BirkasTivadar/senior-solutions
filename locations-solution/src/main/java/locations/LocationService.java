package locations;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class LocationService {

    public static final String SEPARATOR = ",";

    public void writeLocations(Path file, List<Location> locations) {
        {
            try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(file))) {
                for (Location location : locations) {
                    writer.print(location.getName());
                    writer.print(SEPARATOR);
                    writer.print(location.getLat());
                    writer.print(SEPARATOR);
                    writer.println(location.getLon());
                }

            } catch (IOException ioException) {
                throw new IllegalStateException("Can not write file", ioException);
            }
        }
    }
}
