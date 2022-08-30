package co.mahsan.library_manager.repository;

import co.mahsan.library_manager.model.Writer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WriterRepository extends MongoRepository<Writer, String> {
    List<Writer> findByName(String name);
}
