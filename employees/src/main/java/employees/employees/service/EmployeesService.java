package employees.employees.service;

import employees.employees.dto.CreateEmployeeCommand;
import employees.employees.dto.EmployeeDto;
import employees.employees.dto.EmployeeNotFoundException;
import employees.employees.dto.UpdateEmployeeCommand;
import employees.employees.entity.Employee;
import employees.employees.repository.EmployeesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class EmployeesService {

    private ModelMapper modelMapper;

    private EmployeesRepository employeesRepository;

    public List<EmployeeDto> listEmployees(Optional<String> prefix) {
        List<Employee> employees = employeesRepository.findAll();
        Type targetListType = new TypeToken<List<EmployeeDto>>() {}.getType();
        return modelMapper.map(employees, targetListType);
    }

    public EmployeeDto findEmployeeById(long id) {
        return modelMapper.map(employeesRepository
                .findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id)), EmployeeDto.class);
    }

    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        log.info("Create employee");
        log.debug("Create employee with name {}", command.getName());
        Employee employee = new Employee(command.getName());
        employeesRepository.save(employee);
        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Transactional
    public EmployeeDto updateEmployee(UpdateEmployeeCommand command) {
        // begin
        Employee employee = employeesRepository.findById(command.getId())
                .orElseThrow(() -> new EmployeeNotFoundException(command.getId()));
        employee.setName(command.getNewName());

        return modelMapper.map(employee, EmployeeDto.class);
        // commit
    }

    public void deleteEmployee(long id) {
        employeesRepository.deleteById(id);
    }
}
