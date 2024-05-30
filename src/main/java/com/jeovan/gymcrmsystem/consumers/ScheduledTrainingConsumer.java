package com.jeovan.gymcrmsystem.consumers;

import com.jeovan.gymcrmsystem.constants.MessagingQueues;
import com.jeovan.gymcrmsystem.dtos.responses.TrainerMonthReportDTO;
import com.jeovan.gymcrmsystem.services.ScheduledTrainingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ScheduledTrainingConsumer {

    private final ScheduledTrainingService scheduledTrainingService;

    @JmsListener(destination = MessagingQueues.SCHEDULED_HOURS_RESULT_QUEUE)
    public void storeScheduleResults(TrainerMonthReportDTO trainerMonthReportDTO){
        scheduledTrainingService.storeScheduledHoursResult(trainerMonthReportDTO);
        log.info("Scheduled hours received for {}", trainerMonthReportDTO.getUsername());
    }
}
