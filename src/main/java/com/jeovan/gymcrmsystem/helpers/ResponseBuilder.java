package com.jeovan.gymcrmsystem.helpers;

import com.jeovan.gymcrmsystem.dtos.responses.CredentialsDTO;
import com.jeovan.gymcrmsystem.models.SimpleInterface;

public class ResponseBuilder{
    public static CredentialsDTO buildCredentialResponse(SimpleInterface entity){
        return new CredentialsDTO(entity.getUser().getUsername(), entity.getUser().getPassword(), null);
    }
}
