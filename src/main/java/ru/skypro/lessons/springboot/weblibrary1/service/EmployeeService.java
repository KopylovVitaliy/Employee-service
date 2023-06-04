package ru.skypro.lessons.springboot.weblibrary1.service;

import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    double salarySum();
    double minSalary();
    double maxSalary();
    List<Employee> employeeHighSalary();
    void addEmployee();
    Employee getEmployeeById(int id);
    void deleteEmployee(int id);
}