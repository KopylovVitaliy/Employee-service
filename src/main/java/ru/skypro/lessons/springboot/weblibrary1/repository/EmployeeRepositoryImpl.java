package ru.skypro.lessons.springboot.weblibrary1.repository;

import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository{
    private final List<Employee> employeeList = List.of(
            new Employee("Полина", 91_000),
            new Employee("Алиса", 109_000),
            new Employee("Алёна", 87_000),
            new Employee("Влад", 100_000));
    @Override
    public List<Employee> getAllEmployees() {
        return employeeList;
    }
}
