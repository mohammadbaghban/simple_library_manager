package co.mahsan.library_manager.repository;

import co.mahsan.library_manager.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WriterRepository extends MongoRepository<Book, String> {
}
