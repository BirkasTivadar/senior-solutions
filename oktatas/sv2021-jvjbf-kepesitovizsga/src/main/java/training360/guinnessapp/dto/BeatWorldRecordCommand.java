package training360.guinnessapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeatWorldRecordCommand {

    @NotNull
    private Long newRecorderId;

    @NotNull
    private Double newValue;
}
