package ru.skypro.lessons.springboot.weblibrary1.service;

import ru.skypro.lessons.springboot.weblibrary1.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface EmployeeService {

    List<EmployeeDTO> getAllNew();

    double salarySum();

    EmployeeDTO minSalary();

    EmployeeDTO maxSalary();

    List<EmployeeDTO> employeeHighSalary();

    List<EmployeeDTO> addEmployee(List<EmployeeDTO> employeeDTOS);
    void update(int id, EmployeeDTO employeeDTO);

    EmployeeDTO getEmployeeById(int id);

    void deleteEmployee(int id);

    List<EmployeeDTO> salaryHigherThan(Integer than);
}