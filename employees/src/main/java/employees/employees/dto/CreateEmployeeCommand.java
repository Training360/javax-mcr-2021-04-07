package employees.employees.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeCommand {

//    @NotBlank(message = "The name can not be empty")
//    @NotBlank
    @Schema(description = "name of the employee", example = "John Doe")
//    @ValidName

    @ValidName(message = "name must contains three words")
    private String name;
}
