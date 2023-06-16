package ru.skypro.lessons.springboot.weblibrary1.service;

import ru.skypro.lessons.springboot.weblibrary1.dto.ReportDTO;

import java.util.List;

public interface ReportService {
    List<ReportDTO> createReport();
}
