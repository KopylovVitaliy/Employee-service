package weblibraryTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.lessons.springboot.weblibrary1.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary1.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.weblibrary1.service.EmployeeMapper;

import ru.skypro.lessons.springboot.weblibrary1.service.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repositoryMock;
    @Spy
    private EmployeeMapper employeeMapper;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void getAllNewTest() {
        final int id = 1;
        final String inputName = "Vasya";
        final int inputSalary = 20000;
        Employee employee = new Employee(inputName, inputSalary);
        employee.setId(id);
        final List<EmployeeDTO> employees = getIterable(id, inputName, inputSalary);
        final List<Employee> employees1 = List.of(employee);
        when(repositoryMock.findAll())
                .thenReturn(employees1);
        List<EmployeeDTO> actual = employeeService.getAllNew();

        assertEquals(employees.size(), actual.size());

    }

    @Test
    void getMinimumSalaryEmployeeInDepartment() {
        final int id = 1;
        final String inputName = "Vasya";
        final int inputSalary = 20000;
        EmployeeDTO employeeDTO = new EmployeeDTO(id, inputName, inputSalary, "тестировщик");
        when(repositoryMock.minSalary())
                .thenReturn(Optional.of(employeeDTO));

        assertEquals(employeeDTO, employeeService.minSalary());
    }

    @Test
    void getMaximumSalaryEmployeeInDepartment() {
        final int id = 1;
        final String inputName = "Vasya";
        final int inputSalary = 220000;
        List<EmployeeDTO> list = List.of(
                new EmployeeDTO(id, inputName, inputSalary, "тестировщик"), new EmployeeDTO(id, inputName, 300000, "тестировщик"));
        EmployeeDTO employeeDTO = list.stream()
                .max(Comparator.comparing(EmployeeDTO::getSalary)).get();
        when(repositoryMock.maxSalary())
                .thenReturn(list);

        EmployeeDTO actual = employeeService.maxSalary();
        assertEquals(employeeDTO, actual);
    }

    @Test
    void salarySumTest() {
        double sum = 22000;
        when(repositoryMock.salarySum())
                .thenReturn(sum);

        double actual = employeeService.salarySum();
        assertEquals(sum, actual);
    }

    @Test
    void employeeHighSalaryTest() {
        int avg = 10000;
        when(repositoryMock.employeeHighSalary())
                .thenReturn(avg);

        int actual = employeeService.employeeHighSalary();
        assertEquals(avg, actual);
    }

    @Test
    void addEmployeeTest() {
        final int id = 1;
        final String inputName = "Vasya";
        final int inputSalary = 20000;
        Employee employee = new Employee(inputName, inputSalary);
        employee.setId(id);
        List<EmployeeDTO> employeeDTOS = getIterable(id, inputName, inputSalary);
        final List<Employee> employees1 = List.of(employee);

        when(repositoryMock.saveAll(employees1))
                .thenReturn(employees1)
                .thenAnswer(i -> {
                    throw new RuntimeException(" ");
                });

        List<EmployeeDTO> actual = new ArrayList<>();
        actual.add(employeeService.addEmployee(employeeDTOS).get(0));

        assertEquals(employeeDTOS.size(), actual.size());
    }

    @Test
    void updateEmployeeTest() {
        final int id = 1;
        final String inputName = "Vasya";
        final int inputSalary = 20000;

        EmployeeDTO actual = new EmployeeDTO(id, "inputName", 1000, "тестер");
        Employee employee = new Employee(inputName, inputSalary);
        employee.setId(id);

        when(repositoryMock.findById(id))
                .thenReturn(Optional.of(employee));
        when(repositoryMock.save(employee))
                .thenReturn(employee);
        employeeService.update(id, actual);

        assertEquals(employee.getName(), actual.getName());
        assertEquals(employee.getSalary(), actual.getSalary());
    }

    @Test
    void getEmployeeTest() {
        int id = 1;
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName("John");
        employee.setSalary(2222);
        repositoryMock.save(employee);
        when(repositoryMock.findById(employee.getId()))
                .thenReturn(Optional.of(employee))
                .thenAnswer(i -> {
                    throw new RuntimeException(" ");
                });
        EmployeeDTO employee1 = employeeService.getEmployeeById(id);
        assertEquals(employee.getName(), employee1.getName());
        assertEquals(employee.getSalary(), employee1.getSalary());
        assertEquals(employee.getId(), employee1.getId());
    }

    @Test
    void testDeleteEmployee() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("John");
        employee.setSalary(2222);

        when(repositoryMock.findById(1))
                .thenReturn(Optional.of(employee))
                .thenAnswer(i -> {
                    throw new RuntimeException("У—отрудник не найденФ");
                });

        repositoryMock.deleteById(1);
        assertTrue(repositoryMock.findById(1).isPresent());
    }

    @Test
    void salaryHigherThanTest() {
        int than = 1000;
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("John");
        employee.setSalary(2222);
        List<Employee> employees = List.of(employee);

        when(repositoryMock.findEmployeeBySalaryIsGreaterThan(than))
                .thenReturn(employees);
        List<EmployeeDTO> employeeDTOS = employeeService.salaryHigherThan(than);
        assertEquals(employees.size(), employeeDTOS.size());
    }

    @Test
    void withHighestSalaryTest() {
        final int id = 1;
        final String inputName = "Vasya";
        final int inputSalary = 20000;
        List<EmployeeDTO> employees = getIterable(id, inputName, inputSalary);
        when(repositoryMock.maxSalary())
                .thenReturn(employees);
        EmployeeDTO actual = employeeService.maxSalary();

        assertEquals(employees.get(0), actual);
    }

    @Test
    void getEmployeeFullInfoTest() {
        final int id = 1;
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName("John");
        employee.setSalary(2222);
        when(repositoryMock.findById(id))
                .thenReturn(Optional.of(employee));

        EmployeeDTO actual = employeeService.getEmployeeFullInfo(id);
        assertEquals(employee.getName(), actual.getName());
        assertEquals(employee.getSalary(), actual.getSalary());
        assertEquals(employee.getId(), actual.getId());
    }

    @Test
    void getEmployeesFromPage() {
        int page = 3;

        final int id = 1;
        final String inputName = "Vasya";
        final int inputSalary = 20000;
        Employee employee = new Employee(inputName, inputSalary);
        employee.setId(id);

        final List<Employee> employees1 = List.of(employee, employee, employee, employee);
        when(repositoryMock.findAll())
                .thenReturn(employees1);

        List<Employee> actual = repositoryMock.findAll().stream().limit(page).toList();
        assertEquals(actual.size(), page);

    }

    private static List<EmployeeDTO> getIterable(int id,
                                                 String inputName,
                                                 int inputSalary) {

        return List.of(new EmployeeDTO(id, inputName, inputSalary, "тестер")
        );
    }
}
