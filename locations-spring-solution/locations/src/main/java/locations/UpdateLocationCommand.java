package locations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLocationCommand {

    private String name;

    private Double lat;

    private Double lon;

}
