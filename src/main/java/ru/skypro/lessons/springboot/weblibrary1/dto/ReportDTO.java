package ru.skypro.lessons.springboot.weblibrary1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor

@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class ReportDTO {

    private Integer pos;
    private Long count;
    private Integer max;
    private Integer min;
    private Double avg;

    public ReportDTO(Integer pos, Long count, Integer max, Integer min, Double avg) {
        this.pos = pos;
        this.count = count;
        this.max = max;
        this.min = min;
        this.avg = avg;
    }
}
