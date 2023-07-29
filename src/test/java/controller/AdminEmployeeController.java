package controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
public class AdminEmployeeController {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    private void cleanData() {
        employeeRepository.deleteAll();
    }

    @Test
    void givenNoUsersInDatabase_whenUserAdded_thenItExistsInList() throws Exception {
        JSONObject position = new JSONObject();
        position.put("id", 1);
        position.put("name", "Java");

        JSONObject employee = new JSONObject();
        employee.put("salary", 100000);
        employee.put("position_id", position.get("id"));
        employee.put("name", "Ivan");


        mockMvc.perform(post("/admin/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new JSONArray().put(employee).toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        mockMvc.perform(get("/employee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Ivan"))
                .andExpect(jsonPath("$[0].salary").value(100000));
    }

    static List<Employee> employees (int expectedCount){
        return Stream.generate(() ->
                        new Employee("Petr", 12000))
                .limit(expectedCount)
                .toList();

    }
}
