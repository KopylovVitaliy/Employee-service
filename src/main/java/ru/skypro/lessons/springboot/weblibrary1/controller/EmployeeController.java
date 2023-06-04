package ru.skypro.lessons.springboot.weblibrary1.controller;

import org.springframework.web.bind.annotation.*;
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
    @PostMapping
    public void addNewEmployee(){employeeService.addEmployee();}
    @PutMapping("/{id}")
    public void editEmployee(@PathVariable int id, @RequestBody Employee employee){
        employeeService.getEmployeeById(id).setName(employee.getName());
        employeeService.getEmployeeById(id).setSalary(employee.getSalary());
    }
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id){
        return employeeService.getEmployeeById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);
    }

}
