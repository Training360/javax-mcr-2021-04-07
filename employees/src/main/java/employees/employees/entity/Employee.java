package employees.employees.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private Long id;

    private String name;

    public Employee(String name) {
        this.name = name;
    }
}