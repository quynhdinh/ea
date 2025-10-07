package cs544.message;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class Sender implements CommandLineRunner {
	@Autowired
	private KafkaTemplate<String, Book> template;
	@Autowired
	private KafkaTemplate<String, Integer> template2;
	List<Book> books = List.of(
			new Book("Domain Driven Design", "978-0321125217", "Eric Evans", 54.99),
			new Book("Clean Code", "978-0132350884", "Robert C. Martin", 43.99),
			new Book("Design Patterns", "978-0201633610", "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", 54.95),
			new Book("Refactoring", "978-0201485677", "Martin Fowler", 47.99),
			new Book("Patterns of Enterprise Application Architecture", "978-0321127426", "Martin Fowler", 54.99),
			new Book("Test Driven Development: By Example", "978-0321146533", "Kent Beck", 37.99)
	);
	// Book book = new Book(1, "Updated book", "978-0321125217", "Eric Evans", 54.99);
	@Override
	public void run(String... args) throws Exception {
		String topic = "book";
		Book msg = books.get((int) (Math.random() * books.size()));
		template.send(topic, msg);
		System.out.println("Sent: " + msg +" to: " + topic);
		// updateBook(book);
		// sendDelete(1);
	}
	void updateBook(Book book) {
		String topic = "book";
		template.send(topic, book);
		System.out.println("Update " + book +" to: " + topic);
	}
	void sendDelete(int id) {
		String topic = "book";
		template2.send(topic, id);
		System.out.println("Delete book with id: " + id +" to: " + topic);
	}
}

