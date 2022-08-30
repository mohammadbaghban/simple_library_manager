package co.mahsan.library_manager.service;

import co.mahsan.library_manager.Exceptions.PublisherNotFoundException;
import co.mahsan.library_manager.model.Publisher;
import co.mahsan.library_manager.repository.BookRepository;
import co.mahsan.library_manager.repository.PublisherRepository;
import co.mahsan.library_manager.repository.WriterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private final BookRepository bookRepo;
    private final PublisherRepository publisherRepo;
    private final WriterRepository writerRepo;

    public List<Publisher> findAll() {
        return publisherRepo.findAll();
    }

    public Publisher save(Publisher newPublisher) {
        if(newPublisher != null){
            if (publisherRepo.findByName(newPublisher.getName()).isEmpty()){
                newPublisher = publisherRepo.save(newPublisher);
            } else {
                newPublisher.setId(publisherRepo.findByName(newPublisher.getName()).get().getId());
            }
        }
        return newPublisher;
    }

    public Publisher findById(String id) {
        return publisherRepo.findById(id)
                .orElseThrow(() -> new PublisherNotFoundException(id));
    }

    public Publisher replacePublisher(Publisher newPublisher, String id) {
        return publisherRepo.findById(id)
                .map(publisher -> {
                    publisher.setName(newPublisher.getName());
                    return publisherRepo.save(publisher);
                })
                .orElseGet(() -> {
                    newPublisher.setId(id);
                    return publisherRepo.save(newPublisher);
                });
    }

    public void deletePublisherById(String id) {
        publisherRepo.deleteById(id);
    }
}
