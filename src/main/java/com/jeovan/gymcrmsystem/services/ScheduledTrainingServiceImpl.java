package com.jeovan.gymcrmsystem.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.jeovan.gymcrmsystem.constants.MessagingQueues;
import com.jeovan.gymcrmsystem.constants.ScheduledTrainingActions;
import com.jeovan.gymcrmsystem.dtos.requests.TrainerMonthReportRequestDTO;
import com.jeovan.gymcrmsystem.dtos.responses.TrainerMonthReportDTO;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class ScheduledTrainingServiceImpl implements ScheduledTrainingService {
    private final Map<String, Map<String, Double>> workedHoursResults = Maps.newConcurrentMap();

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private SqsTemplate sqsTemplate;
    @Autowired
    private ObjectMapper mapper;


    @Override
    public void sendScheduledHoursRequest(String username, String yearMonth) {
        //jmsTemplate.convertAndSend(MessagingQueues.SCHEDULED_HOURS_QUEUE, TrainerMonthReportRequestDTO.builder().username(username).yearMonth(yearMonth).build());
        try {
            var message = mapper.writeValueAsString(TrainerMonthReportRequestDTO.builder().username(username).yearMonth(yearMonth).build());
            sqsTemplate.send(to -> to.queue(MessagingQueues.SCHEDULED_HOURS_QUEUE).payload(message));
            log.info("Requested scheduled hours for {}", username);
        }catch (JsonProcessingException e){
            log.error("Unable to request scheduled hours for {}", username);
        }
    }

    @Override
    public void storeScheduledHoursResult(TrainerMonthReportDTO trainerMonthReport) {
        Map<String, Double> monthSummary = workedHoursResults.getOrDefault(trainerMonthReport.getUsername(), Maps.newConcurrentMap());
        monthSummary.put(trainerMonthReport.getYearMonth(), trainerMonthReport.getMinutes());
        workedHoursResults.put(trainerMonthReport.getUsername(), monthSummary);
    }

    @Override
    public Optional<Double> getScheduledHoursResult(String username, String yearMonth) {
        return Optional.ofNullable(workedHoursResults.getOrDefault(username, Maps.newConcurrentMap()).get(yearMonth));
    }
}
