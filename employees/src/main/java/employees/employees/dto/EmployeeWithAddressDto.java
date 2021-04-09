package employees.employees.dto;

import employees.employees.gateway.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeWithAddressDto {

    private Long id;

    private String name;

    private AddressDto address;
}
