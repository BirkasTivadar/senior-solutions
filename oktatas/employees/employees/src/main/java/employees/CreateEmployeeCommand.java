package employees;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeCommand {

    //    @NotBlank(message = "Name can not be blank")
//    @Name(message = "Ne legyen üres")
//    @Name(maxLength = 20)
    @Schema(description = "name of the employee", example = "John Doe")
    @Name
    private String name;

}
