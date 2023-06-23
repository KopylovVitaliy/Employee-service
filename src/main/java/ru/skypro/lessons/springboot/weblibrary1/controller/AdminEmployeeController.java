package ru.skypro.lessons.springboot.weblibrary1.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.weblibrary1.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary1.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("admin/employee")
public class AdminEmployeeController {
    private final EmployeeService employeeService;

    public AdminEmployeeController(EmployeeService employeeService) {
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
    }
    @GetMapping("withHighestSalary")
    public List<EmployeeDTO> salaryWithHighestSalary() {
        return employeeService.withHighestSalary();
    }
    @GetMapping
    public List<EmployeeDTO> getEmployeesForPosition(@RequestParam(required = false) String position) {
        return employeeService.getEmployee(
                Optional.ofNullable(position)
                        .filter(p -> !p.isEmpty())
                        .orElse(null));
    }
    @GetMapping("/{id}/fullInfo")
    public EmployeeDTO getEmployeeFullInfo(@PathVariable int id) {
        return employeeService.getEmployeeFullInfo(id);
    }
    @GetMapping("/page")
    public List<EmployeeDTO> getEmployeesFromPage(@RequestParam(required = false, defaultValue = "0")  int page) {
        return employeeService.getEmployeesFromPage(page);

    }
}