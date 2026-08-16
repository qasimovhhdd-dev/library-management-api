package com.yourname.library.file;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Scheduled(fixedRate = 60000)
    public void logCleanupCheck() {
        System.out.println("Scheduled task işə düşdü: " + java.time.LocalDateTime.now());
    }
}