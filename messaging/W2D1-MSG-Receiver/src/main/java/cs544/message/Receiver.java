package cs544.message;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
	
	@KafkaListener(topics = "hello")
	public void receive(@Payload Person msg) {
		System.out.println("Received: " + msg.getName());
	}
}
