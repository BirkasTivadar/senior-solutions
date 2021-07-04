package org.training360.musicstore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInstrumentCommand {

    @NotBlank(message = "Brand can not be blank")
    private String brand;

    private InstrumentType type;

    @PositiveOrZero(message = "Price can not be negative")
    private Integer price;

}

