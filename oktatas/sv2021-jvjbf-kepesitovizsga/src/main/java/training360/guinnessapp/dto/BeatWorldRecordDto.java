package training360.guinnessapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeatWorldRecordDto {

    private String description;

    private String unitOfMeasure;

    private RecorderDto oldRecorderDto;

    private RecorderDto newRecorderDto;

    private double recordDifference;
}
