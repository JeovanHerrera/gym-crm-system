package com.jeovan.gymcrmsystem.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
    @JoinColumn(name = "user_id")
    private User user;
    private UUID specializationId;
}
