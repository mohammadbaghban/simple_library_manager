package co.mahsan.library_manager.repository;

import co.mahsan.library_manager.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findByName(String name); // todo comment: findAllByName behtar nist?
}
