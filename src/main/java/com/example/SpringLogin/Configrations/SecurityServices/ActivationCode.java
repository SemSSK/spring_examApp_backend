package com.example.SpringLogin.Configrations.SecurityServices;

import lombok.Data;


@Data
public class ActivationCode {
    private final int MAX_ATTEMPTS_ALLOWED = 3;
    private String code;
    private Long timeLeft;
    private int attempts = 0;

    public ActivationCode(String code)
    {
        this.code = code;
        timeLeft = System.currentTimeMillis() + 3 * 60 * 1000;
    }

    public boolean isValid(){
        return timeLeft > System.currentTimeMillis();
    }

    public boolean incrementAttempts(){
        attempts++;
        return attempts < MAX_ATTEMPTS_ALLOWED;
    }
}
