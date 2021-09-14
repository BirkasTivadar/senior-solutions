package training360.guinnessapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecorderDto {

    private Long id;

    private String name;

    private LocalDate dateOfBirth;

    private WorldRecordDto worldRecord;
}
