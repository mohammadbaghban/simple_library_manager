package co.mahsan.library_manager.service;

import co.mahsan.library_manager.Exceptions.BookNotFoundException;
import co.mahsan.library_manager.Exceptions.PublisherNotFoundException;
import co.mahsan.library_manager.model.Book;
import co.mahsan.library_manager.model.Writer;
import co.mahsan.library_manager.repository.BookRepository;
import co.mahsan.library_manager.repository.PublisherRepository;
import co.mahsan.library_manager.repository.WriterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepo;
    private final PublisherRepository publisherRepo;
    private final WriterRepository writerRepo;

    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    public Book save(Book newBook) {

        if(newBook.getPublisherId() != null){
            if (publisherRepo.findById(newBook.getPublisherId()).isEmpty()){
                throw new PublisherNotFoundException(newBook.getPublisherId());
            }
        }

        return bookRepo.save(newBook);
    }

    public Book findById(String id) {
        return bookRepo.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book replaceBook(Book newBook, String id) {
        return bookRepo.findById(id)
                .map(Book -> {
                    Book.setName(newBook.getName());
                    Book.setPublisherId(newBook.getPublisherId());
                    Book.setWritersId(newBook.getWritersId());
                    return bookRepo.save(Book);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return bookRepo.save(newBook);
                });
    }

    public void deleteBookById(String id) {
        bookRepo.deleteById(id);
    }
}
