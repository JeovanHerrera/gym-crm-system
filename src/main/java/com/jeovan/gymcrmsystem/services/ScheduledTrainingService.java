package com.jeovan.gymcrmsystem.services;

import com.jeovan.gymcrmsystem.dtos.responses.TrainerMonthReportDTO;

import java.util.Optional;

public interface ScheduledTrainingService {
    void sendScheduledHoursRequest(String username, String yearMonth);

    void storeScheduledHoursResult(TrainerMonthReportDTO trainerMonthReport);

    Optional<Double> getScheduledHoursResult(String username, String yearMonth);
}
