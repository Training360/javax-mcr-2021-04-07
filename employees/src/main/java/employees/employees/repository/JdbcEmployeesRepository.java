package employees.employees.repository;

import employees.employees.dto.EmployeeDto;
import employees.employees.entity.Employee;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
@AllArgsConstructor
public class JdbcEmployeesRepository {

    private JdbcTemplate jdbcTemplate;

    private static Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Employee(resultSet.getLong("id"), resultSet.getString("emp_name"));
    }

    public List<Employee> findAll() {
        return jdbcTemplate.query("select id, emp_name from employees", JdbcEmployeesRepository::mapRow);
    }

    public List<EmployeeDto> findAllDtos() {
        return jdbcTemplate.query("select id, emp_name from employees", new RowMapper<EmployeeDto>() {
            @Override
            public EmployeeDto mapRow(ResultSet resultSet, int i) throws SQLException {
                return new EmployeeDto(resultSet.getLong("id"), resultSet.getString("emp_name"));
            }
        });
    }

    public Employee findById(long id) {
        return jdbcTemplate.queryForObject("select id, emp_name from employees where id = ?",
                JdbcEmployeesRepository::mapRow, id);
    }

    public void save(Employee employee) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                con -> {
                    PreparedStatement ps =
                            con.prepareStatement("insert into employees(emp_name) values (?)",
                                    Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, employee.getName());
                    return ps;
                }, keyHolder);
        employee.setId(keyHolder.getKey().longValue());
    }

    public void update(Employee employee) {
        jdbcTemplate.update("update employees set emp_name = ? where id = ?",
                employee.getName(), employee.getId());
    }

    public void delete(long id) {
        jdbcTemplate.update("delete from employees where id = ?", id);
    }


}
