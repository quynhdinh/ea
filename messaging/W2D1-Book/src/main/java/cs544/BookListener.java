package cs544;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import cs544.message.Book;

@Component
@KafkaListener(topics = "book")
public class BookListener {
    @Autowired
    private BookService bookService;
    
    @KafkaHandler
    public void addBook(Book book) {
        bookService.add(book);
        System.out.println("Book added/updated: " + book);
    }
    @KafkaHandler
    public void deleteBook(Integer id) {
        bookService.delete(id);
        System.out.println("Book deleted with id: " + id);
    }
}
