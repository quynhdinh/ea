package edu.cs544.monitoring_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
public class MonitoringAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitoringAppApplication.class, args);
	}

}
@RestController
@Slf4j
class MonitoringController {

    @GetMapping("/info-log")
    public String infoLog() {
        log.info("INFO: /info-log endpoint accessed. Everything is operating normally.");
        return "Generated INFO log.";
    }

    @GetMapping("/error-log")
    public String errorLog() {
        log.error("ERROR: /error-log endpoint accessed. This simulates a critical issue!");
        // For a real error, you might throw an exception: throw new RuntimeException("Simulated application error");
        return "Generated ERROR log.";
    }
}