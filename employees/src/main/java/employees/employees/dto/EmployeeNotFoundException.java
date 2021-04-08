package employees.employees.dto;

public class EmployeeNotFoundException extends RuntimeException {

    private long id;

    public EmployeeNotFoundException(long id) {
        super("Employee not found: " + id);
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
