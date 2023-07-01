package ru.skypro.lessons.springboot.weblibrary1.service;

import jakarta.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ru.skypro.lessons.springboot.weblibrary1.dto.EmployeeDTO;

import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary1.repository.EmployeeRepository;


import java.util.*;


import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);


    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<EmployeeDTO> getAllNew() {
        return null;
    }

    @Override
    public double salarySum() {
        logger.debug("Метод подсчёта суммы зарплат сотрудников.");
        return employeeRepository.salarySum();
    }

    @Override
    public EmployeeDTO minSalary() {
        logger.debug("Метод поиска минимальной зарплаты среди сотрудников.");
        return employeeRepository.minSalary()
                .orElse(null);
    }

    @Override
    public EmployeeDTO maxSalary() {
        logger.debug("Метод поиска максимальной зарплаты среди сотрудников.");
        List<EmployeeDTO> withHighestSalary = withHighestSalary();
        return withHighestSalary.get(0);
    }

    @Override
    public List<EmployeeDTO> employeeHighSalary() {
        logger.debug("Метод поиска средней зарплаты среди сотрудников.");
        int avg = employeeRepository.employeeHighSalary();
        return salaryHigherThan(avg);
    }

    @Override
    public List<EmployeeDTO> addEmployee(List<EmployeeDTO> employeeDTOS) {
        logger.debug("Метод добавления сотрудников.");
        Optional<EmployeeDTO> emploees = employeeDTOS.stream()
                .filter(employeeDTO -> employeeDTO.getSalary() <= 0 || employeeDTO.getName() == null
                        || employeeDTO.getName().isEmpty())
                .findFirst();
        if (emploees.isPresent()) {
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
        logger.debug("Метод обновления сотрудника с {} id", id);
        Employee oldEmployee = employeeRepository.findById(id)
                .orElseThrow(()->{
                    logger.error("сотрудник не найден. id =" + id);
                    return new RuntimeException();
                });
        oldEmployee.setName(employeeDTO.getName());
        oldEmployee.setSalary(employeeDTO.getSalary());
        employeeRepository.save(oldEmployee);
    }

    @Override
    public EmployeeDTO getEmployeeById(int id) {
        logger.debug("Метод поиска сотрудника с {} id", id);
        return employeeRepository.findById(id)
                .map(employeeMapper::toDto)
                .map(employeeDTO -> {
                    employeeDTO.setPosition(null);
                    return employeeDTO;
                })
                .orElseThrow(()->{
                    logger.error("сотрудник не найден. id =" + id);
                    return new RuntimeException();
                });

    }

    @Override
    public void deleteEmployee(int id) {
        logger.debug("Метод удаления сотрудника с {} id", id);
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->{
                    logger.error("сотрудник не найден. id =" + id);
                    return new RuntimeException();
                });
        employeeRepository.delete(employee);
    }

    @Override
    public List<EmployeeDTO> salaryHigherThan(Integer than) {
        logger.debug("Метод сортировки сотрудников с зарплатой выше {}", than);
        return employeeRepository.findEmployeeBySalaryIsGreaterThan(than)
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> withHighestSalary() {
        logger.debug("Метод поиска сотрудников с самыми высокими зарплатами.");
        return employeeRepository.maxSalary();
    }

    @Override
    public List<EmployeeDTO> getEmployee(@Nullable String position) {
        logger.debug("Метод поиска сотрудников на позиции {}", position);
        return Optional.ofNullable(position)
                .map(employeeRepository::findEmployeeByPosition_Position)
                .orElseGet(employeeRepository::findAll)
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeFullInfo(int id) {
        logger.debug("Метод получения полной информации о сотруднике по id {}", id);
        return employeeRepository.findById(id)
                .map(employeeMapper::toDto)
                .orElseThrow(()->{
                    logger.error("сотрудник не найден. id =" + id);
                    return new RuntimeException();
                });

    }

    @Override
    public List<EmployeeDTO> getEmployeesFromPage(int page) {
        logger.debug("Метод получения cписка сотрудников на странице {}", page);
        return employeeRepository.findAll(PageRequest.of(page, 10))
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());

    }
}