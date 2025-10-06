package cs544.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender implements CommandLineRunner {
	@Autowired
	private KafkaTemplate<String, Person> template;
	
	@Override
	public void run(String... args) throws Exception {
		String queue = "hello";
		Person msg = new Person("Frank");
		template.send(queue, msg);
		System.out.println("Sent: " + msg +" to: " + queue);
	}
}
