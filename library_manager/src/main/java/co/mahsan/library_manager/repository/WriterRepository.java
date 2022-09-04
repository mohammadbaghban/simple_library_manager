package co.mahsan.library_manager.repository;

import co.mahsan.library_manager.model.Writer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WriterRepository extends MongoRepository<Writer, String> {

}
