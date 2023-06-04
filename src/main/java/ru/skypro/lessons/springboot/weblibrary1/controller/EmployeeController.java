package ru.skypro.lessons.springboot.weblibrary1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary1.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all-employee")
    public List<Employee> showCounter() {
        return employeeService.getAllEmployees();
    }
    @GetMapping("/salary/sum")
    public double getSalarySum(){
        return employeeService.salarySum();
    }
    @GetMapping("/salary/min")
    public double getSalaryMin(){
        return employeeService.minSalary();
    }
    @GetMapping("/salary/max")
    public double getSalaryMax(){
        return employeeService.maxSalary();
    }
    @GetMapping("/salary/high-salary")
    public List<Employee> getEmployeeHighSalary(){
        return employeeService.employeeHighSalary();
    }
    @GetMapping
    public List<Employee> getNewEmployee(){return employeeService.addPlentyEmployee();}
}
