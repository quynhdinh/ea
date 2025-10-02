package edu.miu.cs544.spring_websocket_postgresql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.OffsetDateTime;
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

    public Message() {} // Default constructor required by JPA

    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timestamp = OffsetDateTime.now();
    }

    // only getters, no setters (immutable after creation)
    public Long getId() { return id; }
    public String getSender() { return sender; }
    public String getContent() { return content; }
    public OffsetDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", sender='" + sender + '\'' + ", content='" + content + '\'' + ", timestamp=" + timestamp + '}';
    }
}

@Repository
interface MessageRepository extends JpaRepository<Message, Long> {}

