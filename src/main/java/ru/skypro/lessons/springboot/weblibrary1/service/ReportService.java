package ru.skypro.lessons.springboot.weblibrary1.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary1.dto.ReportDTO;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ReportService {
    Integer createReport() throws IOException;

    void upload(File file) throws IOException, ClassNotFoundException;

    Report getReportById(int id);
}
