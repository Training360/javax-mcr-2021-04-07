package employees;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeesService {

    private final List<Employee> employees =
            new ArrayList<>(List.of(
                    new Employee(1L, "John Doe"),
                    new Employee(2L, "Jane Doe")
            ));

    private ModelMapper modelMapper;

    public List<EmployeeDto> listEmployees(Optional<String> prefix) {
        return employees.stream()
                .filter(e -> prefix.isEmpty() || e.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
                //.map(e -> new EmployeeDto(e.getName()))
                .map(e -> modelMapper.map(e, EmployeeDto.class))
                //.toList();
                .collect(Collectors.toList());

//        Type targetListType = new TypeToken<List<EmployeeDto>>() {}.getType();
        // Ilyen NINCS: List<EmployeeDto>.class
//        return modelMapper.map(employees, targetListType);
    }

    public EmployeeDto findEmployeeById(long id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .map(e -> modelMapper.map(e, EmployeeDto.class))
                .findAny().orElseThrow(
                        () -> new IllegalArgumentException("Employee not found with id" + id));
    }
}
