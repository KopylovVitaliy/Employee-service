package ru.skypro.lessons.springboot.weblibrary1.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary1.dto.ReportDTO;

import java.util.List;

public interface ReportService {
    List<ReportDTO> createReport();
    void upload(MultipartFile file) throws JsonProcessingException;
}
