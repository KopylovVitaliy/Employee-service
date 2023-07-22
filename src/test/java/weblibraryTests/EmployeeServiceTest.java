package weblibraryTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.lessons.springboot.weblibrary1.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Position;
import ru.skypro.lessons.springboot.weblibrary1.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.weblibrary1.service.EmployeeMapper;
import ru.skypro.lessons.springboot.weblibrary1.service.EmployeeServiceImpl;


import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repositoryMock;
    @Mock
    private EmployeeMapper employeeMapper;
    @InjectMocks
    private EmployeeServiceImpl out;
    @Test
    void getAllNewTest(){
        final int id = 1;
        final String inputName = "Vasya";
        final int inputSalary = 20000;
        final List<EmployeeDTO> employees = getIterable(id, inputName, inputSalary);
        when(repositoryMock.findAll().stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList()))
                .thenReturn(employees);


        assertIterableEquals(employees, out.getAllNew());

    }

    @Test
    void getMinimumSalaryEmployeeInDepartment(){
        final int id = 1;
        final String inputName = "Vasya";
        final int inputSalary = 20000;
        EmployeeDTO employeeDTO = new EmployeeDTO(id, inputName, inputSalary, "тестировщик");
        when(repositoryMock.minSalary())
                .thenReturn(Optional.of(employeeDTO));

        assertEquals(employeeDTO, out.minSalary());
    }
    @Test
    void getMaximumSalaryEmployeeInDepartment(){
        final int id = 1;
        final String inputName = "Vasya";
        final int inputSalary = 220000;
        List<EmployeeDTO> list = List.of(
        new EmployeeDTO(id, inputName, inputSalary, "тестировщик"), new EmployeeDTO(id, inputName, 300000, "тестировщик"));
        EmployeeDTO employeeDTO = list.stream()
                .max(Comparator.comparing(EmployeeDTO::getSalary)).get();
        when(repositoryMock.maxSalary())
                .thenReturn(list);

        EmployeeDTO actual = out.maxSalary();
        assertEquals(employeeDTO, actual);
    }

    @Test
    void salarySumTest(){
        double sum = 22000;
        when(repositoryMock.salarySum())
                .thenReturn(sum);

        double actual = out.salarySum();
        assertEquals(sum, actual);
    }

    @Test
    void employeeHighSalaryTest(){
        int avg = 10000;
        when(repositoryMock.employeeHighSalary())
                .thenReturn(avg);

        int actual = out.employeeHighSalary();
        assertEquals(avg, actual);
    }

    @Test
    void addEmployeeTest(){
        final int id = 1;
        final String inputName = "Vasya";
        final int inputSalary = 20000;
        List<EmployeeDTO> employeeDTOS = getIterable(id, inputName, inputSalary);

        when(repositoryMock.saveAll(employeeDTOS.stream()
                        .map(employeeMapper::toEntity)
                        .collect(Collectors.toList())
                )
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList()))
                .thenReturn(employeeDTOS);

        List<EmployeeDTO> actual = out.addEmployee(employeeDTOS);
        assertEquals(employeeDTOS, actual);
    }

//    @Test
//    void updateEmployeeTest(){
//        final int id = 1;
//        final String inputName = "Vasya";
//        final int inputSalary = 20000;
//        EmployeeDTO employeeDTO = new EmployeeDTO(id, "inputName", 1000, "тестер");
//        Employee employee = new Employee(inputName, inputSalary);
//        when(repositoryMock.save(employee))
//                .thenReturn(employee);
//        out.update(id, employeeDTO);
//
//        Employee employee1 = new Employee(employeeDTO.getName(), employeeDTO.getSalary());
//        employee.setSalary(employeeDTO.getSalary());
//        assertEquals(employee, employeeDTO);
//    }

    @Test
    void getEmployeeTest(){
        final int id = 1;
        final String inputName = "Vasya";
        final int inputSalary = 20000;
        Employee employee = new Employee( "inputName", 1000);
        when(repositoryMock.findById(id))
                .thenReturn(Optional.of(employee));

        Employee employee1 = new Employee(out.getEmployeeById(id).getName(), out.getEmployeeById(id).getSalary());
        assertEquals(employee, employee1);
    }


    private static List<EmployeeDTO> getIterable(int id,
                                                  String inputName,
                                                  int inputSalary){

        return List.of(new EmployeeDTO(id, inputName, inputSalary, "тестер")
        );
    }
    @Test
    public void testFindEmployeeById() {
        Employee employee = new Employee("John Smith", 111);

        when(repositoryMock.findById(1)).thenReturn(Optional.of(employee));
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);
        EmployeeDTO employeeDto = out.getEmployeeById(1);

        assertEquals("John Smith", employeeDto.getName());
        assertEquals(111, employeeDto.getSalary());
    }

    public static EmployeeDTO toDto(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setPosition(Optional.ofNullable(employee.getPosition())
                .map(Position::getPosition)
                .orElse(null));
        return employeeDTO;
    }
    public static Employee toEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());

        return employee;
    }
}
