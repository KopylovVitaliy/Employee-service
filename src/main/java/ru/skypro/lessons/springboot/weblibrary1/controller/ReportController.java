package ru.skypro.lessons.springboot.weblibrary1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary1.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary1.dto.ReportDTO;
import ru.skypro.lessons.springboot.weblibrary1.service.EmployeeService;
import ru.skypro.lessons.springboot.weblibrary1.service.ReportService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ReportController {
    private final ReportService reportService;
    private final EmployeeService employeeService;

    public ReportController(ReportService reportService, EmployeeService employeeService) {
        this.reportService = reportService;
        this.employeeService = employeeService;
    }

    @GetMapping("/report")
    public Integer createReport() throws IOException {
        return reportService.createReport();
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@RequestParam("file")MultipartFile multipartFile) throws IOException, ClassNotFoundException {
       File file = new File(multipartFile.getName());
       Files.write(file.toPath(), multipartFile.getBytes());

       reportService.upload(file);
    }
    @GetMapping(value = "/report/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> downloadFile(@PathVariable int id) {
        String fileName = reportService.getReportById(id).getFile();
        String json = readTextFromFile(fileName);

        Resource resource = new ByteArrayResource(json.getBytes());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(resource);
    }
    private static String readTextFromFile(String fileName) {
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

