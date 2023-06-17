package ru.skypro.lessons.springboot.weblibrary1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary1.dto.ReportDTO;
import ru.skypro.lessons.springboot.weblibrary1.service.ReportService;

import java.util.List;

@RestController
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/rep")
    public List<ReportDTO> createReport(){
        return reportService.createReport();
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@RequestParam("file")MultipartFile file) throws JsonProcessingException {
        reportService.upload(file);
    }

}
