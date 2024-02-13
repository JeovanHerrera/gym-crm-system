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
    String username;
    String password;
    String newPassword;
}
