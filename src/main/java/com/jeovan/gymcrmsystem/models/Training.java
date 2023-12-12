package com.jeovan.gymcrmsystem.models;

import java.util.Date;
import java.util.UUID;

public class Training {
    private UUID id;
    private Date trainingDate;
    private String trainingName;
    private Trainee trainee;
    private Trainer trainer;
    private TrainingType specialization;
    private Long trainingDuration;
}
