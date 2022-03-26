package com.example.SpringLogin.LogInWork;

import lombok.Data;

import java.util.HashMap;

@Data
public class ActivationCode {

    private String code;
    private Long timeLeft;
    public static HashMap<String,ActivationCode> codesMap = new HashMap<>();

    public ActivationCode(String code)
    {
        this.code = code;
        timeLeft = System.currentTimeMillis() + 3 * 60 * 1000;
    }

    public boolean isValid(){
        return timeLeft > System.currentTimeMillis();
    }


}
