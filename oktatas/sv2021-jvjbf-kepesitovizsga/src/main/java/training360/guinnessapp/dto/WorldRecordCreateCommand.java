package training360.guinnessapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorldRecordCreateCommand {

    @NotBlank(message = "must not be blank")
    private String description;

    @NotNull
    private Double value;

    @NotBlank
    private String unitOfMeasure;

    @NotNull
    private LocalDate dateOfRecord;

    @NotNull
    private Long recorderId;
}
