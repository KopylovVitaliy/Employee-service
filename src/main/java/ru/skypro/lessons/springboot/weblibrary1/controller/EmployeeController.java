package ru.skypro.lessons.springboot.weblibrary1.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.weblibrary1.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary1.service.EmployeeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/salary/sum")
    public double getSalarySum() {
        return employeeService.salarySum();
    }

    @GetMapping("/salary/min")
    public EmployeeDTO getSalaryMin() {
        return employeeService.minSalary();
    }

    @GetMapping("/salary/max")
    public EmployeeDTO getSalaryMax() {
        return employeeService.maxSalary();
    }

    @GetMapping("/salary/high-salary")
    public List<EmployeeDTO> getEmployeeHighSalary() {
        return employeeService.employeeHighSalary();
    }

    @PostMapping
    public List<EmployeeDTO> addNewEmployee(@RequestBody List<EmployeeDTO> employeeDTOS) {
        return employeeService.addEmployee(employeeDTOS);
    }

    @PutMapping("/{id}")
    public void editEmployee(@PathVariable int id, @RequestBody EmployeeDTO employeeDTO) {
        employeeService.update(id, employeeDTO);
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/all-employee-new")
    public List<EmployeeDTO> all() {
        return employeeService.getAllNew();
    }

    @GetMapping("salaryHigherThan")
    public List<EmployeeDTO> salaryHigherThan(@RequestParam("salary") Integer compareSalary) {
        return employeeService.salaryHigherThan(compareSalary);
    }    @GetMapping("withHighestSalary")
    public List<EmployeeDTO> salaryWithHighestSalary() {
        return employeeService.withHighestSalary();
    }
}
