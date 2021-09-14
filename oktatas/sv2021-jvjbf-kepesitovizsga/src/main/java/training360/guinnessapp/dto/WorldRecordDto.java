package training360.guinnessapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private RecorderDto recorder;

    public String getRecorderName() {
        return recorder.getName();
    }
}
