package ru.skypro.lessons.springboot.weblibrary1.service;

import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary1.repository.EmployeeRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
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
    public List<Employee> addPlentyEmployee() {
        List<Employee> list = List.of(
                new Employee("Иван", 97_000),
                new Employee("Евгений", 180_000),
                new Employee("Владислав", 82_000),
                new Employee("Александр", 130_000)
        );
        return list;
    }
}