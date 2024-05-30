package com.jeovan.gymcrmsystem.services;

import com.google.common.collect.Maps;
import com.jeovan.gymcrmsystem.constants.MessagingQueues;
import com.jeovan.gymcrmsystem.dtos.requests.TrainerMonthReportRequestDTO;
import com.jeovan.gymcrmsystem.dtos.responses.TrainerMonthReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ScheduledTrainingServiceImpl implements ScheduledTrainingService {
    private final Map<String, Map<String, Double>> workedHoursResults = Maps.newConcurrentMap();

    @Autowired
    JmsTemplate jmsTemplate;

    @Override
    public void sendScheduledHoursRequest(String username, String yearMonth) {
        jmsTemplate.convertAndSend(MessagingQueues.SCHEDULED_HOURS_QUEUE, TrainerMonthReportRequestDTO.builder().username(username).yearMonth(yearMonth).build());
    }

    @Override
    public void storeScheduledHoursResult(TrainerMonthReportDTO trainerMonthReport) {
        Map<String, Double> monthSummary = workedHoursResults.getOrDefault(trainerMonthReport.getUsername(), Maps.newConcurrentMap());
        monthSummary.put(trainerMonthReport.getYearMonth(), trainerMonthReport.getMinutes());
        workedHoursResults.put(trainerMonthReport.getUsername(), monthSummary);
    }

    @Override
    public double getScheduledHoursResult(String username, String yearMonth) {
        return workedHoursResults.getOrDefault(username, Maps.newConcurrentMap()).getOrDefault(yearMonth, 0.0);
    }
}
