package employees.employees.service;

import employees.employees.dto.*;
import employees.employees.entity.Employee;
import employees.employees.gateway.AddressDto;
import employees.employees.gateway.AddressesGateway;
import employees.employees.repository.EmployeesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Address;
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

    private AddressesGateway addressesGateway;

    public List<EmployeeDto> listEmployees(Optional<String> prefix) {
        List<Employee> employees = employeesRepository.findAll();
        Type targetListType = new TypeToken<List<EmployeeDto>>() {}.getType();
        return modelMapper.map(employees, targetListType);
    }

    public EmployeeWithAddressDto findEmployeeById(long id) {
        EmployeeWithAddressDto employee = modelMapper.map(employeesRepository
                .findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id)), EmployeeWithAddressDto.class);

        AddressDto address = addressesGateway.getAddressByName(employee.getName());
        employee.setAddress(address);

        return employee;
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
