package com.jeovan.gymcrmsystem.services;

import com.jeovan.gymcrmsystem.dtos.responses.TrainerMonthReportDTO;

public interface ScheduledTrainingService {
    void sendScheduledHoursRequest(String username, String yearMonth);

    void storeScheduledHoursResult(TrainerMonthReportDTO trainerMonthReport);

    double getScheduledHoursResult(String username, String yearMonth);
}
