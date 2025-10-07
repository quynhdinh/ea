package com.cs544.scheduled_logging;

import java.time.LocalDateTime;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;

@SpringBootApplication
@EnableScheduling
public class ScheduledLoggingApplication {
	public static void main(String[] args) {
		SpringApplication.run(ScheduledLoggingApplication.class, args);
	}
}
class MyCustomEvent extends ApplicationEvent {
	public MyCustomEvent(Object source) {
		super(source);
	}
}
@Component
class MyProducer {
	private static final Logger log = (Logger) LoggerFactory.getLogger(MyProducer.class);
	private final ApplicationEventPublisher applicationEventPublisher;

	public MyProducer(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Scheduled(fixedRate = 3000)
	public void produceEvent() {
		String message = "Hello at " + LocalDateTime.now();
		log.info("Producing event: {}", message);
		MyCustomEvent event = new MyCustomEvent(this);
		applicationEventPublisher.publishEvent(event);
	}

}
@Component
class MyListener {
	private static final Logger log = (Logger) LoggerFactory.getLogger(MyListener.class);
	@EventListener
	public void handleCustomEvent(MyCustomEvent event) {
		log.info("Received custom event: {}", event);
	}
}