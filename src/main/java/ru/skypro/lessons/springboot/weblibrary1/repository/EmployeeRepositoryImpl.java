package ru.skypro.lessons.springboot.weblibrary1.repository;

import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private List<Employee> employeeList = List.of(
            new Employee("Полина", 91_000),
            new Employee("Алиса", 109_000),
            new Employee("Алёна", 87_000),
            new Employee("Влад", 100_000));
    private final HashMap<Integer, Employee> employeeMap = new HashMap<>();

    @Override
    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    @Override
    public HashMap<Integer, Employee> getNewEmployees() {
        return employeeMap;
    }

    @Override
    public void addEmployee() {
        Employee employee = new Employee("Полина", 91_000);
        Employee employee1 = new Employee("Алиса", 109_000);
        Employee employee2 = new Employee("Алёна", 87_000);
        Employee employee3 = new Employee("Влад", 100_000);
        employeeMap.put(employee.getId(), employee);
        employeeMap.put(employee1.getId(), employee1);
        employeeMap.put(employee2.getId(), employee2);
        employeeMap.put(employee3.getId(), employee3);

    }

    @SneakyThrows
    @Override
    public Employee getEmployeeById(int id) {
        if (!employeeMap.containsKey(id)) {
            throw new IOException();
        }
        return employeeMap.get(id);
    }

    @SneakyThrows
    @Override
    public void deleteEmployee(int id) throws IOException {
        if (!employeeMap.containsKey(id)) {
            throw new IOException();
        }
        employeeMap.remove(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeRepositoryImpl that = (EmployeeRepositoryImpl) o;
        return Objects.equals(employeeList, that.employeeList) && Objects.equals(employeeMap, that.employeeMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeList, employeeMap);
    }
}
