package cs544;

import java.util.List;
import java.util.Optional;

import jakarta.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookService {
    @Resource
    private IBookDao bookDao;
    
    public List<Book> getAll() {
        return bookDao.findAll();
    }

    public void add(Book book) {
        bookDao.save(book);
    }

    public Optional<Book> get(int id) {
        return bookDao.findById(id);
    }

    public void update(Book book) {
        bookDao.save(book);
    }

    public void delete(int id) {
        bookDao.deleteById(id);
    }
}