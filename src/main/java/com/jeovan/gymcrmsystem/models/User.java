package com.jeovan.gymcrmsystem.models;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements SimpleInterface{

    private UUID id;
    private String firstName;
    private String lastName;
    private String password;
    private Boolean isActive;
}
