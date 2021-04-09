package employees.employees;

import employees.employees.controller.EmployeesController;
import employees.employees.dto.CreateEmployeeCommand;
import employees.employees.dto.EmployeeDto;
import employees.employees.dto.EmployeeWithAddressDto;
import employees.employees.gateway.AddressesGateway;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql(statements = "delete from employees")
public class EmployeesIT {

    @Autowired
    EmployeesController employeesController;

    @MockBean
    private AddressesGateway addressesGateway;

    //@Test
    @RepeatedTest(2)
    void testSaveThanQuery() {
        employeesController
                .createEmployee(new CreateEmployeeCommand("Jane Doe"));
        employeesController
                .createEmployee(new CreateEmployeeCommand("John Doe"));

        List<EmployeeDto> employees = employeesController
                .listEmployees(Optional.empty());

        assertThat(employees)
                .extracting(EmployeeDto::getName)
                .containsExactly("Jane Doe", "John Doe");
    }

    @Test
    void testSaveThanGet() {
        EmployeeDto employee = employeesController
                .createEmployee(new CreateEmployeeCommand("Jane Doe"));

        EmployeeWithAddressDto details = employeesController.findEmployeeById(employee.getId());

        assertThat(details.getName())
                .isEqualTo("Jane Doe");
    }
}
