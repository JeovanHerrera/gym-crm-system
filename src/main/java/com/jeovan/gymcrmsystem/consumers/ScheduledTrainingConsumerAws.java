package com.jeovan.gymcrmsystem.consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeovan.gymcrmsystem.constants.MessagingQueues;
import com.jeovan.gymcrmsystem.dtos.responses.TrainerMonthReportDTO;
import com.jeovan.gymcrmsystem.services.ScheduledTrainingService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
@Profile("local")
public class ScheduledTrainingConsumerAws {
    private final ScheduledTrainingService scheduledTrainingService;
    private final ObjectMapper mapper;

    @SqsListener(MessagingQueues.SCHEDULED_HOURS_RESULT_QUEUE)
    public void storeScheduleResults(String trainerMonthReportDTOMessage) throws JsonProcessingException {
        TrainerMonthReportDTO trainerMonthReportDTO = mapper.readValue(trainerMonthReportDTOMessage, TrainerMonthReportDTO.class);
        scheduledTrainingService.storeScheduledHoursResult(trainerMonthReportDTO);
        log.info("Scheduled hours received for {}", trainerMonthReportDTO.getUsername());
    }
}
