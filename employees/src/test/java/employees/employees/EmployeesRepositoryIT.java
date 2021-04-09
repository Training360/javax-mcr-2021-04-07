package employees.employees;

import employees.employees.entity.Employee;
import employees.employees.repository.EmployeesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class EmployeesRepositoryIT {

    @Autowired
    EmployeesRepository employeesRepository;

    @Test
    void testSaveAndFind() {
        employeesRepository.deleteAll();

        Employee employee = new Employee("John Doe");
        employeesRepository.save(employee);
//        long id = employee.getId();
//
//        Employee loadedEmployee = employeesRepository.findById(id).get();
//        assertEquals("John Doe", loadedEmployee.getName());

        List<Employee> employees = employeesRepository.findAll();
        assertThat(employees)
                .extracting(Employee::getName)
                .containsExactly("John Doe");
    }
}
