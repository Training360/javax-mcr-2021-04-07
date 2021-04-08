package employees;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeesService {

    private List<Employee> employees =
            new ArrayList<>(List.of(
                    new Employee("John Doe"),
                    new Employee("Jane Doe")
            ));

    public List<EmployeeDto> listEmployees() {
        employees.get(0).getName();
        return employees.stream()
                .map(e -> new EmployeeDto(e.getName()))
                //.toList();
                .collect(Collectors.toList());
    }
}
