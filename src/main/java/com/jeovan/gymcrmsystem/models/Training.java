package com.jeovan.gymcrmsystem.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Training{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private Date trainingDate;

    @Column(nullable = false)
    private String trainingName;

    @ManyToOne
    @Cascade({CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "trainee_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference(value = "trainee")
    private Trainee trainee;

    @ManyToOne
    @Cascade({CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "trainer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference(value = "trainer")
    private Trainer trainer;

    @ManyToOne
    @Cascade({CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "training_type_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TrainingType trainingType;

    @Column(nullable = false)
    private Long trainingDuration;

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", trainingDate=" + trainingDate +
                ", trainingName='" + trainingName + '\'' +
                ", trainee=" + trainee.getUser().getUsername() +
                ", trainer=" + trainer.getUser().getUsername() +
                ", trainingType=" + trainingType.getTrainingTypeName() +
                ", trainingDuration=" + trainingDuration +
                '}';
    }
}
