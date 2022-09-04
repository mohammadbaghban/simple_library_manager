package co.mahsan.library_manager.repository;

import co.mahsan.library_manager.model.Publisher;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PublisherRepository extends MongoRepository<Publisher, String> {

}
