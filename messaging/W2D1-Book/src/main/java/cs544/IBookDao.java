package cs544;

import org.springframework.data.jpa.repository.JpaRepository;

import cs544.message.Book;

public interface IBookDao extends JpaRepository<Book, Integer> {
}