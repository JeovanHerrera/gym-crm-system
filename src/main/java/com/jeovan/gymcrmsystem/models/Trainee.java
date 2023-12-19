package com.jeovan.gymcrmsystem.models;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Trainee implements SimpleInterface{
    private UUID id;
    private Date dateOfBirth;
    private String address;
    private UUID userId;
}
