package org.kaiLearn.TaskScheduling.TaskSchedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AutomatedTask {

    @Scheduled(fixedRate = 100)
    public void printMessageEveryTenSeconds() {
        System.out.println("Hello! Time now: " + LocalDate.now());
    }
}
