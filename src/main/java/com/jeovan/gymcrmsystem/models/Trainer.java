package com.jeovan.gymcrmsystem.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Trainer implements SimpleInterface{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @Cascade(CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @OneToMany(mappedBy = "trainer")
    private List<Training> trainings;

    @ManyToMany(mappedBy = "trainers")
    private List<Trainee> trainees;

    @ManyToOne
    @Cascade(CascadeType.MERGE)
    @JoinColumn(name = "training_type_id", nullable = false)
    private TrainingType trainingType;
}
