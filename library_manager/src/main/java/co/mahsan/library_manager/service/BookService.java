package co.mahsan.library_manager.service;

import co.mahsan.library_manager.BookNotFoundException;
import co.mahsan.library_manager.model.Book;
import co.mahsan.library_manager.model.Writer;
import co.mahsan.library_manager.repository.BookRepository;
import co.mahsan.library_manager.repository.PublisherRepository;
import co.mahsan.library_manager.repository.WriterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

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
        //newBook.setPublisher(publisherRepo.save(newBook.getPublisher()));
        if(newBook.getPublisher() != null){
            if (publisherRepo.findByName(newBook.getPublisher().getName()).isEmpty()){
                newBook.setPublisher(publisherRepo.save(newBook.getPublisher()));
            } else {
                newBook.setPublisher(newBook.getPublisher().setId(publisherRepo.findByName(newBook.getPublisher().getName()).get().getId()));
            }
        }
        for (Writer writer:
             newBook.getWriters()) {
            writer = writerRepo.save(writer);

        }

//        newBook.setPublisher(Optional.ofNullable(newBook.getPublisher())
//                .map(publisher -> publisherRepo.findByName(publisher.getName())
//                        .orElse(publisherRepo.save(publisher)))
//                .orElse(null));
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
                    Book.setPublisher(newBook.getPublisher());
                    Book.setWriters(newBook.getWriters());
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
