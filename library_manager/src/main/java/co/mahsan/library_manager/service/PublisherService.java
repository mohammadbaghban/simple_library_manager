package co.mahsan.library_manager.service;

import co.mahsan.library_manager.Exceptions.PublisherNotFoundException;
import co.mahsan.library_manager.mappers.PublisherMapper;
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


    public List<PublisherDTO> findAll() {
        List<Publisher> publishers = publisherRepo.findAll();
        List<PublisherDTO> publisherDTOS = new ArrayList<>();
        for (Publisher publisher:
                publishers) {
            publisherDTOS.add(PublisherMapper.INSTANCE.publisherToPublisherDTO(publisher));
        }
        return publisherDTOS;
    }

    public PublisherDTO save(PublisherDTO newPublisherDTO) {
        Publisher newPublisher = PublisherMapper.INSTANCE.publisherDTOToPublisher(newPublisherDTO);
        if(newPublisher != null){ // todo comment: age null bashe chi? fekr kardi behesh?
            if (!publisherRepo.findByName(newPublisher.getName()).isPresent()){
                newPublisher = publisherRepo.save(newPublisher);
            } else {
                newPublisher.setId(publisherRepo.findByName(newPublisher.getName()).get().getId()); // todo comment: alan dari ino 2 bar findByName mikoni
            }
        }
        return PublisherMapper.INSTANCE.publisherToPublisherDTO(newPublisher);
    }

    public PublisherDTO findById(String id) {
        return PublisherMapper.INSTANCE.publisherToPublisherDTO(publisherRepo.findById(id)
                .orElseThrow(() -> new PublisherNotFoundException(id)));
    }

    public PublisherDTO replacePublisher(PublisherDTO newPublisher, String id) {
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
