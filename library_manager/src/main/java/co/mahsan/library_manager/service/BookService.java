package co.mahsan.library_manager.service;

import co.mahsan.library_manager.BookNotFoundException;
import co.mahsan.library_manager.model.Book;
import co.mahsan.library_manager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    public List<Book> findAll(){
        return repository.findAll();
    }

    public Book save(Book newBook){
        return repository.save(newBook);
    }

    public Book findById(String id){
        return repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book replaceBook(Book newBook, String id) {
        return repository.findById(id)
                .map(Book -> {
                    Book.setName(newBook.getName());
                    Book.setPublisher(newBook.getPublisher());
                    Book.setWriters(newBook.getWriters());
                    return repository.save(Book);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return repository.save(newBook);
                });
    }

    public void deleteBookById(String id) {
        repository.deleteById(id);
    }
}
