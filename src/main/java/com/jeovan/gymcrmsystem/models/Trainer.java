package com.jeovan.gymcrmsystem.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trainer implements SimpleInterface{
    private UUID id;
    private UUID userId;
    private UUID specializationId;
}
