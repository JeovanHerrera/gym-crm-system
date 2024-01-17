package com.jeovan.gymcrmsystem.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Training implements SimpleInterface{
    @Id
    private UUID id;
    private Date trainingDate;
    private String trainingName;
    private UUID traineeId;
    private UUID trainerId;
    private UUID specializationId;
    private Long trainingDuration;
}
