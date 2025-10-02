package edu.miu.cs544.spring_websocket_postgresql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.OffsetDateTime;
import java.util.List;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}

@Entity
@Getter
@Setter
@AllArgsConstructor
class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    @Version
    private Long version;
}

// only getter, no setter for immutability
@Getter
@Entity
@Table(name = "messages")
class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String sender;

    @Column(nullable = false, updatable = false)
    private String content;

    @Column(updatable = false)
    private OffsetDateTime timestamp;

    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timestamp = OffsetDateTime.now();
    }
	public Message() {
		// default constructor
	}

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", sender='" + sender + '\'' + ", content='" + content + '\'' + ", timestamp=" + timestamp + '}';
    }
}

@Repository
interface MessageRepository extends JpaRepository<Message, Long> {}

@Controller
class MessageController {
    private final MessageRepository messageRepository;

    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // Define GraphQL queries and mutations here
	@QueryMapping
	public List<Message> messages() {
		return messageRepository.findAll();
	}

	@QueryMapping
	public Message messageById(@Argument Long id) {
		return messageRepository.findById(id).orElse(null);
	}

	@MutationMapping
	public Message createMessage(@Argument String content, @Argument String sender) {
		Message message = new Message(sender, content);
		return messageRepository.save(message);
	}
}