package edu.cs544.logging_exc.SchedulingExercise;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableScheduling
public class SchedulingExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulingExerciseApplication.class, args);
	}
}

@Component
@Slf4j
class MyScheduledTasks {

    @Scheduled(cron = "0/4 * * * * *")
    public void reportCurrentTime() {
        log.info("The current time is: {}", LocalDateTime.now());
    }
}