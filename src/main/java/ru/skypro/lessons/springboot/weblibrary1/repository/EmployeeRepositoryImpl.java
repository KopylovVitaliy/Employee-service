package ru.skypro.lessons.springboot.weblibrary1.repository;

import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository{
    private List<Employee> employeeList = List.of(
            new Employee("Полина", 91_000),
            new Employee("Алиса", 109_000),
            new Employee("Алёна", 87_000),
            new Employee("Влад", 100_000));
    private final List<Employee> employeeList1= new ArrayList<>();
    @Override
    public List<Employee> getAllEmployees() {
        return employeeList;
    }
    @Override
    public void addEmployee() {
        employeeList1.addAll(employeeList);
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeList1.get(id - 1);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeList1.remove(id-1);
    }
}
