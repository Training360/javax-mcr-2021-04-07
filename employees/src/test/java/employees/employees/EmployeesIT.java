package employees.employees;

import employees.employees.controller.EmployeesController;
import employees.employees.dto.CreateEmployeeCommand;
import employees.employees.dto.EmployeeDto;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql(statements = "delete from employees")
public class EmployeesIT {

    @Autowired
    EmployeesController employeesController;

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
}
