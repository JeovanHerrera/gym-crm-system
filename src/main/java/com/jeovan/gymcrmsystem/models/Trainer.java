package com.jeovan.gymcrmsystem.models;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Trainer implements SimpleInterface{
    private UUID id;
    private UUID userId;
    private UUID specializationId;
}
