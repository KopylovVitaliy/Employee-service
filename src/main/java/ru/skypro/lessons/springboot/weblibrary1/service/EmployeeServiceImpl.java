package ru.skypro.lessons.springboot.weblibrary1.service;

import jakarta.annotation.Nullable;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import ru.skypro.lessons.springboot.weblibrary1.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary1.exrptions.EmployeeExceptionHandler;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary1.repository.EmployeeRepository;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @PostConstruct
    public void init(){
        employeeRepository.deleteAll();
        employeeRepository.saveAll(
                List.of(
                        new Employee("Alena", 140000),
                        new Employee("Polina", 200000),
                        new Employee("Vlad", 100000)
                )
        );
    }

    @Override
    public List<EmployeeDTO> getAllNew() {
        return null;
    }

    @Override
    public double salarySum() {
       return employeeRepository.salarySum();
    }

    @Override
    public EmployeeDTO minSalary() {
        return employeeRepository.minSalary()
                .orElse(null);
    }

    @Override
    public EmployeeDTO maxSalary() {
        List<EmployeeDTO> withHighestSalary = withHighestSalary();
        return withHighestSalary.get(0) ;
    }

    @Override
    public List<EmployeeDTO> employeeHighSalary() {
     int avg = employeeRepository.employeeHighSalary();
     return salaryHigherThan(avg);
    }

    @Override
    public List<EmployeeDTO> addEmployee(List<EmployeeDTO> employeeDTOS) {
        Optional<EmployeeDTO> emploees = employeeDTOS.stream()
                .filter(employeeDTO -> employeeDTO.getSalary() <= 0 || employeeDTO.getName() == null
                || employeeDTO.getName().isEmpty())
                .findFirst();
        if(emploees.isPresent()){
            throw new RuntimeException();
        }
        return employeeRepository.saveAll(
                employeeDTOS.stream()
                        .map(employeeMapper::toEntity)
                        .collect(Collectors.toList())
        )
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void update(int id, EmployeeDTO employeeDTO) {
        Employee oldEmployee = employeeRepository.findById(id)
                .orElseThrow();
        oldEmployee.setName(employeeDTO.getName());
        oldEmployee.setSalary(employeeDTO.getSalary());
        employeeRepository.save(oldEmployee);
    }

    @Override
    public EmployeeDTO getEmployeeById(int id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toDto)
                .map(employeeDTO -> {
                    employeeDTO.setPosition(null);
                    return employeeDTO;
                })
                .orElseThrow(RuntimeException::new);

    }

    @Override
    public void deleteEmployee(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow();
        employeeRepository.delete(employee);
    }

    @Override
    public List<EmployeeDTO> salaryHigherThan(Integer than) {
        return employeeRepository.findEmployeeBySalaryIsGreaterThan(than)
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<EmployeeDTO> withHighestSalary(){
        return employeeRepository.maxSalary();
    }

    @Override
    public List<EmployeeDTO> getEmployee(@Nullable String position) {
        return Optional.ofNullable(position)
                .map(employeeRepository::findEmployeeByPosition_Position)
                .orElseGet(employeeRepository::findAll)
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public EmployeeDTO getEmployeeFullInfo(int id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toDto)
                .orElseThrow(RuntimeException::new);

    }
    @Override
    public List<EmployeeDTO> getEmployeesFromPage(int page){
        return employeeRepository.findAll(PageRequest.of(page, 10))
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());

    }
}