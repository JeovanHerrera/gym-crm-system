package com.jeovan.gymcrmsystem.helpers.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Credentials {
    private String username;
    private String password;
    private String newPassword;
}
