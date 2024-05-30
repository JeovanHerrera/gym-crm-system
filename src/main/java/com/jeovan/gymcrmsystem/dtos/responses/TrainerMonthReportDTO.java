package com.jeovan.gymcrmsystem.dtos.responses;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainerMonthReportDTO {
    private String username;
    private String yearMonth;
    private Double minutes;
}
