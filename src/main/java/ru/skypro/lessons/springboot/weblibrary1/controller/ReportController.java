package ru.skypro.lessons.springboot.weblibrary1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary1.dto.ReportDTO;
import ru.skypro.lessons.springboot.weblibrary1.service.EmployeeService;
import ru.skypro.lessons.springboot.weblibrary1.service.ReportService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
public class ReportController {
    private final ReportService reportService;
    private final EmployeeService employeeService;

    public ReportController(ReportService reportService, EmployeeService employeeService) {
        this.reportService = reportService;
        this.employeeService = employeeService;
    }

    @GetMapping("/report")
    public List<ReportDTO> createReport() throws IOException {
        return reportService.createReport();
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@RequestParam("file")MultipartFile multipartFile) throws IOException, ClassNotFoundException {
       File file = new File(multipartFile.getName());
       Files.write(file.toPath(), multipartFile.getBytes());

       reportService.upload(file);
    }
    @GetMapping(value = "/report/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Resource downloadFile(@PathVariable int id) throws JsonProcessingException {
        Resource resource = new ByteArrayResource(reportService.getReportById(id).getFile().getBytes());
        return resource;
    }

}
