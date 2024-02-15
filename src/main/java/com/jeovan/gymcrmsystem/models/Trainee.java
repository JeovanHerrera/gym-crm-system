package com.jeovan.gymcrmsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.List;
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
    @JoinColumn(name = "user_id")
    @Cascade(CascadeType.PERSIST)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @OneToMany(mappedBy = "trainee")
    @JsonManagedReference(value = "trainee")
    private List<Training> trainings;

    @ManyToMany
    @JoinTable(name = "trainee_trainer",
            joinColumns = @JoinColumn(name = "trainee_id"),
            inverseJoinColumns = @JoinColumn(name = "trainer_id"))
    private List<Trainer> trainers;
}
