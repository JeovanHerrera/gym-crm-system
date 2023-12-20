package com.jeovan.gymcrmsystem.models;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Training implements SimpleInterface{
    private UUID id;
    private Date trainingDate;
    private String trainingName;
    private UUID traineeId;
    private UUID trainerId;
    private UUID specializationId;
    private Long trainingDuration;
}
