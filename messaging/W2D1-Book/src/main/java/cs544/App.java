/*​​​​​​​​​​​​​​​​​​​​​‌‌​​‌‌​​‌‌‌ See LICENSE.md for the source code license */

package cs544;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	@Bean
	public NewTopic bookTopic() {
		return TopicBuilder.name("book").build();
	}
}
