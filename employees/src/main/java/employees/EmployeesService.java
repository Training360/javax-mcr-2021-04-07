package employees;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeesService {

    private final List<Employee> employees =
            new ArrayList<>(List.of(
                    new Employee("John Doe"),
                    new Employee("Jane Doe")
            ));

    private ModelMapper modelMapper;

    public List<EmployeeDto> listEmployees() {
        /*return employees.stream()
                //.map(e -> new EmployeeDto(e.getName()))
                .map(e -> modelMapper.map(e, EmployeeDto.class))
                //.toList();
                .collect(Collectors.toList());*/

        Type targetListType = new TypeToken<List<EmployeeDto>>() {}.getType();
        // Ilyen NINCS: List<EmployeeDto>.class
        return modelMapper.map(employees, targetListType);
    }
}
