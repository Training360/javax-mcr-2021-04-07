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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class EmployeesService {

    private ModelMapper modelMapper;

    private EmployeesRepository employeesRepository;

    public List<EmployeeDto> listEmployees(Optional<String> prefix) {
        //List<Employee> employees = employeesRepository.findAll();
        //Type targetListType = new TypeToken<List<EmployeeDto>>() {}.getType();
        //return modelMapper.map(employees, targetListType);

        return employeesRepository.findAllDtos();
    }

    public EmployeeDto findEmployeeById(long id) {
        return modelMapper.map(employeesRepository.findById(id), EmployeeDto.class);
    }

    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        log.info("Create employee");
        log.debug("Create employee with name {}", command.getName());
        Employee employee = new Employee(command.getName());
        employeesRepository.save(employee);
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public EmployeeDto updateEmployee(UpdateEmployeeCommand command) {
        Employee employee = new Employee(command.getId(), command.getNewName());
        employeesRepository.update(employee);
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public void deleteEmployee(long id) {
        employeesRepository.delete(id);
    }
}
