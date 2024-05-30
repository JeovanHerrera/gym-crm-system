package com.jeovan.gymcrmsystem.dtos.requests;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainerMonthReportRequestDTO {
    private String username;
    private String yearMonth;
}
