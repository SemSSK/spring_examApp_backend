package com.example.SpringLogin.LogInWork;

import lombok.Data;

import java.util.HashMap;

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
