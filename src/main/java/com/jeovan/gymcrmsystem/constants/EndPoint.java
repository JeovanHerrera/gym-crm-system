package com.jeovan.gymcrmsystem.constants;

public class EndPoint {
    public static final String TRAINEE = "/trainee";
    public static final String TRAINER = "/trainer";
    public static final String TRAINING = "/training";
    public static final String TRAINING_TYPE = "/training-types";
    public static final String LOGIN = "/login";
    public static final String TRAINEE_USERNAME = TRAINEE + "/{username}";
    public static final String TRAINER_USERNAME = TRAINER + "/{username}";

    public static final String TRAINEE_DELETE = TRAINEE + "/delete";

    public static final String TRAINEE_RESET_PASSWORD = TRAINEE + "/password";
    public static final String TRAINER_RESET_PASSWORD = TRAINER + "/password";
}
