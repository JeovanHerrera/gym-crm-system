package com.jeovan.gymcrmsystem.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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

    @Column(nullable = false)
    private Date trainingDate;

    @Column(nullable = false)
    private String trainingName;

    @ManyToOne
    @Cascade({CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    @JoinColumn(name = "trainee_id")
    private Trainee trainee;

    @ManyToOne
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @ManyToOne
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "training_type_id")
    private TrainingType trainingTypeId;

    @Column(nullable = false)
    private Long trainingDuration;
}
