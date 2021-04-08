package employees.employees;

import employees.employees.controller.EmployeesController;
import employees.employees.dto.EmployeeDto;
import employees.employees.service.EmployeesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = EmployeesController.class)
public class EmployeesControllerIT {

    @MockBean
    EmployeesService employeesService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testListEmployees() throws Exception {
        // Given
        when(employeesService.listEmployees(any())).thenReturn(
                Arrays.asList(
                        new EmployeeDto(1L, "John Doe Test"),
                        new EmployeeDto(2L, "Jane Doe Test")
                )
        );

        // When
        mockMvc.perform(get("/api/employees"))
                .andDo(print())

                // Then - assert

        .andExpect(status().isOk()) // 200-as státuszkód?
        .andExpect(jsonPath("$[1].name", equalTo("Jane Doe Test")));

    }
}
