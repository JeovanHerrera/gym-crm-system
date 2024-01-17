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
public class Trainee implements SimpleInterface{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private Date dateOfBirth;
    private String address;
    @OneToOne
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
