package co.mahsan.library_manager.repository;

import co.mahsan.library_manager.model.Publisher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PublisherRepository extends MongoRepository<Publisher, String> {
    Optional<Publisher> findByName(String name); // todo comment: age chand ta Publisher ba yek name dashte bashim chi?
}
