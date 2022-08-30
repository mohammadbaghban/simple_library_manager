package co.mahsan.library_manager.repository;

import co.mahsan.library_manager.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findByName(String name);
}
