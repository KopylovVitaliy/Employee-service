package ru.skypro.lessons.springboot.weblibrary1.service;

import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary1.repository.EmployeeRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    @Override
    public HashMap<Integer, Employee> getAllNew() {
        return employeeRepository.getNewEmployees();
    }

    @Override
    public double salarySum() {
        return employeeRepository.getAllEmployees().stream()
                .mapToDouble(Employee::getSalary)
                .sum();

    }

    @Override
    public double minSalary() {
        return employeeRepository.getAllEmployees().stream()
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow().getSalary();
    }

    @Override
    public double maxSalary() {
        return employeeRepository.getAllEmployees().stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow().getSalary();
    }

    @Override
    public List<Employee> employeeHighSalary() {
        double high = salarySum();
        int size = employeeRepository.getAllEmployees().size();
        return employeeRepository.getAllEmployees().stream()
                .filter(a -> a.getSalary() > high / size)
                .collect(Collectors.toList());
    }

    @Override
    public void addEmployee() {
        employeeRepository.addEmployee();
    }

    @Override
    public Employee getEmployeeById(int id){
        return employeeRepository.getEmployeeById(id);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteEmployee(id);
    }

    @Override
    public List<Map.Entry<Integer, Employee>> salaryHigherThan(Integer than) {
        return  employeeRepository.getNewEmployees().entrySet().stream()
                .filter(e -> e.getValue().getSalary() > than)
                .collect(Collectors.toList());
    }
}