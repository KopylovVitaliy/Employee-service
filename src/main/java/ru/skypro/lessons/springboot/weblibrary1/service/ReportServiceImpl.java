package ru.skypro.lessons.springboot.weblibrary1.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary1.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary1.dto.ReportDTO;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Report;
import ru.skypro.lessons.springboot.weblibrary1.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.weblibrary1.repository.ReportRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public ReportServiceImpl(ReportRepository reportRepository, EmployeeRepository employeeRepository, EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.reportRepository = reportRepository;
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }
    @Override
    public List<ReportDTO> createReport() throws IOException {
        File file = new File("Report.json");
        String s = reportRepository.createReport().toString();
        try (FileOutputStream fileOutputStream = new FileOutputStream(file.getName());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(s);
        }
        Report report = new Report();
        report.setFile(file.getPath());
        reportRepository.save(report);
        return reportRepository.createReport();
    }
    @Override
    public void upload(File file) throws IOException, ClassNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        String content = readTextFromFile(file.getName());
        List<EmployeeDTO> employeeDTOS = objectMapper.readValue(file, new TypeReference<>() {});
        employeeService.addEmployee(employeeDTOS);

    }
    @Override
    public Report getReportById(int id){
       return reportRepository.findById(id)
               .orElseThrow();
    }

    private static String readTextFromFile(String fileName){
        Path path = Paths.get(fileName);
        try {
            return Files.lines(path)
                    .collect(Collectors.joining());
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "";
        }
    }
}
