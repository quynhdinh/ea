/*​​​​​​​​​​​​​​​​​​​​​‌‌​​‌‌​‌​​​ See LICENSE.md for the source code license */

package cs544.message;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class App {

	@Bean
	public NewTopic hello() {
		return TopicBuilder.name("book").build();
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}

