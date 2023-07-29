package controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.lessons.springboot.weblibrary1.WebLibrary1Application;
import ru.skypro.lessons.springboot.weblibrary1.controller.EmployeeController;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Position;
import ru.skypro.lessons.springboot.weblibrary1.repository.EmployeeRepository;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest(classes = WebLibrary1Application.class)
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    private void cleanData() {
        employeeRepository.deleteAll();
    }

    @Test
    @SneakyThrows
    void getEmployeesTest() {
        int expectedCount = 5;
        mockMvc.perform(get("/employee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
        employeeRepository.saveAll(employees(expectedCount));
        mockMvc.perform(get("/employee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(expectedCount));

    }
    static List<Employee> employees (int expectedCount){
        return Stream.generate(() ->
                new Employee("Petr", 12000))
                .limit(expectedCount)
                .toList();

    }
}