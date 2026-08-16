package com.yourname.library.file;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Async
    public void sendEmailNotification(String to, String message) {
        try {
            System.out.println("Email göndərilir: " + to + " -> " + message);
            Thread.sleep(5000);
            System.out.println("Email göndərildi: " + to);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}