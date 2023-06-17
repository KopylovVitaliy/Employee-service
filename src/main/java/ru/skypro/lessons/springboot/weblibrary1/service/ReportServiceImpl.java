package ru.skypro.lessons.springboot.weblibrary1.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary1.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary1.dto.ReportDTO;
import ru.skypro.lessons.springboot.weblibrary1.repository.ReportRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }
    @Override
    public List<ReportDTO> createReport(){
        return reportRepository.createReport();
    }
    @Override
    public void upload(MultipartFile file) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String content = readTextFromFile(file.getName());
        EmployeeDTO employeeDTO = objectMapper.readValue(content, EmployeeDTO.class);
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
