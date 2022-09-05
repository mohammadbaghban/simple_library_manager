package co.mahsan.library_manager.service;

import co.mahsan.library_manager.exception.PublisherNotFoundException;
import co.mahsan.library_manager.mapper.PublisherMapper;
import co.mahsan.library_manager.model.Publisher;
import co.mahsan.library_manager.model.PublisherDTO;
import co.mahsan.library_manager.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private final PublisherRepository publisherRepo;


    public List<PublisherDTO> findAll() {//todo comment: stream
        List<Publisher> publishers = publisherRepo.findAll();
        List<PublisherDTO> publisherDTOS = new ArrayList<>();
        for (Publisher publisher:
                publishers) {
            publisherDTOS.add(PublisherMapper.INSTANCE.publisherToPublisherDTO(publisher));
        }
        return publisherDTOS;
    }

    public PublisherDTO save(PublisherDTO publisherDTO) {
        Publisher newPublisher = PublisherMapper.INSTANCE.publisherDTOToPublisher(publisherDTO);
        if(newPublisher == null){
            throw new PublisherNotFoundException(publisherDTO.getId()); //todo comment: PublisherNotFound inja doroste?
        }
        newPublisher = publisherRepo.save(newPublisher);
        return PublisherMapper.INSTANCE.publisherToPublisherDTO(newPublisher);
    }

    public PublisherDTO findById(String id) {
        return PublisherMapper.INSTANCE.publisherToPublisherDTO(publisherRepo.findById(id)
                .orElseThrow(() -> new PublisherNotFoundException(id)));
    }

    public PublisherDTO replacePublisher(PublisherDTO newPublisher, String id) {//todo comment: esme newPublisher baraye dto khoob inst
        return PublisherMapper.INSTANCE.publisherToPublisherDTO(publisherRepo.findById(id)
                .map(publisherDTO -> {
                    publisherDTO.setName(newPublisher.getName());
                    return publisherRepo.save(publisherDTO);
                })
                .orElseGet(() -> {
                    newPublisher.setId(id);
                    return publisherRepo.save(PublisherMapper.INSTANCE.publisherDTOToPublisher(newPublisher));
                }));
    }

    public void deletePublisherById(String id) {
        publisherRepo.deleteById(id);
    }
}
