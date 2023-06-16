package ru.skypro.lessons.springboot.weblibrary1.service;

import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary1.dto.ReportDTO;
import ru.skypro.lessons.springboot.weblibrary1.repository.ReportRepository;

import java.util.List;
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
}
