package com.jeovan.gymcrmsystem.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AuthTokenDTO {
    private String message;
    public List<String> data;
    public List<String> error;
    public boolean success;
}
