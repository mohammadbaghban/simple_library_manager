package co.mahsan.library_manager.repository;

import co.mahsan.library_manager.model.Writer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface WriterRepository extends MongoRepository<Writer, String> {
    Optional<Writer> findByName(String name);
}
