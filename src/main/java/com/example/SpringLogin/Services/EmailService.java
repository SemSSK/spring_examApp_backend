package com.example.SpringLogin.Services;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendTextEmail(String to,String subject,String text);
}
