package employees.employees.service;

import employees.employees.dto.CreateEmployeeCommand;
import employees.employees.dto.EmployeeDto;
import employees.employees.dto.EmployeeNotFoundException;
import employees.employees.dto.UpdateEmployeeCommand;
import employees.employees.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class EmployeesService {

    private final AtomicLong idGenerator = new AtomicLong();

    private final List<Employee> employees =
            new ArrayList<>(List.of(
                    new Employee(idGenerator.incrementAndGet(), "John Doe"),
                    new Employee(idGenerator.incrementAndGet(), "Jane Doe")
            ));

    private ModelMapper modelMapper;

    public List<EmployeeDto> listEmployees(Optional<String> prefix) {
        return employees.stream()
                // Java 9: prefix.isEmpty()
                .filter(e -> !prefix.isPresent() || e.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
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
                        () -> new EmployeeNotFoundException(id));
    }

    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        log.info("Create employee");
        log.debug("Create employee with name {}", command.getName());
        Employee employee = new Employee(idGenerator.incrementAndGet(), command.getName());
        employees.add(employee);
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public EmployeeDto updateEmployee(UpdateEmployeeCommand command) {
        Employee employee = employees.stream()
                .filter(e -> e.getId() == command.getId())
                .findAny().orElseThrow(
                        () -> new EmployeeNotFoundException(command.getId()));
        employee.setName(command.getNewName());
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public void deleteEmployee(long id) {
        employees.removeIf(e -> e.getId() == id);
    }
}
