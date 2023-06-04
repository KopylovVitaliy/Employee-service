package ru.skypro.lessons.springboot.weblibrary1.service;

import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    HashMap<Integer, Employee> getAllNew();
    double salarySum();
    double minSalary();
    double maxSalary();
    List<Employee> employeeHighSalary();
    void addEmployee();
    Employee getEmployeeById(int id);
    void deleteEmployee(int id);
    List<Map.Entry<Integer, Employee>> salaryHigherThan(Integer than);
}