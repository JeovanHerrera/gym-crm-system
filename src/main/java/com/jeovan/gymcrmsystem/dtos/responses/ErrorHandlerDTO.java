package com.jeovan.gymcrmsystem.dtos.responses;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ErrorHandlerDTO {
    private String type;
    private String username;
}
