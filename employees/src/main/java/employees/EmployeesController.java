package employees;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class EmployeesController {

    private final EmployeesService employeesService;

    @GetMapping("/api/employees")
    public List<EmployeeDto> listEmployees() {
        return employeesService.listEmployees();
    }

}
