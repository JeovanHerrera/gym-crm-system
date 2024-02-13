package com.jeovan.gymcrmsystem.helpers;

import com.jeovan.gymcrmsystem.helpers.responses.Credentials;
import com.jeovan.gymcrmsystem.models.SimpleInterface;
import com.jeovan.gymcrmsystem.models.Trainee;

public class ResponseBuilder{
    public static Credentials buildCredentialResponse(SimpleInterface entity){
        return new Credentials(entity.getUser().getUsername(), entity.getUser().getPassword(), null);
    }
}
