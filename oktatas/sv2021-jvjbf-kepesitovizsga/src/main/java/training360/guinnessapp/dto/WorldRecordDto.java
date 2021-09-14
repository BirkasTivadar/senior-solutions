package training360.guinnessapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import training360.guinnessapp.Recorder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorldRecordDto {

    private Long id;

    private String description;

    private Double value;

    private String unitOfMeasure;

    private LocalDate dateOfRecord;

    private Recorder recorder;

    public String getRecorderName() {
        return recorder.getName();
    }
}
