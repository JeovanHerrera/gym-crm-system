package com.jeovan.gymcrmsystem.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Trainer implements SimpleInterface{
    @Id
    private UUID id;

    @OneToOne
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "training_type_id", nullable = false)
    private TrainingType trainingTypeId;
}
