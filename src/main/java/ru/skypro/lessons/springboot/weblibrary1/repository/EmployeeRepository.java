package ru.skypro.lessons.springboot.weblibrary1.repository;

import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface EmployeeRepository {
    List<Employee> getAllEmployees();
    HashMap<Integer, Employee> getNewEmployees();
    void addEmployee();
    Employee getEmployeeById(int id) throws IOException;
    void deleteEmployee(int id) throws IOException;
}
