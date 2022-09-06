package co.mahsan.library_manager.service;

import co.mahsan.library_manager.util.exception.PublisherNotFoundException;
import co.mahsan.library_manager.mapper.PublisherMapper;
import co.mahsan.library_manager.model.Publisher;
import co.mahsan.library_manager.model.PublisherDto;
import co.mahsan.library_manager.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private final PublisherRepository publisherRepo;
    private final PublisherMapper publisherMapper;

    public List<PublisherDto> findAll() {
        return publisherRepo.findAll().stream().map(publisherMapper::publisherToPublisherDTO).collect(Collectors.toList());
    }

    public PublisherDto save(PublisherDto publisherDTO) {
        Publisher newPublisher = publisherMapper.publisherDTOToPublisher(publisherDTO);
        newPublisher = publisherRepo.save(newPublisher);
        return publisherMapper.publisherToPublisherDTO(newPublisher);
    }

    public PublisherDto findById(String id) {
        return publisherMapper.publisherToPublisherDTO(publisherRepo.findById(id)
                .orElseThrow(() -> new PublisherNotFoundException(id)));
    }

    public PublisherDto update(PublisherDto publisherDto, String id) {
        return publisherMapper.publisherToPublisherDTO(publisherRepo.findById(id)
                .map(publisherDTO -> {
                    publisherDTO.setName(publisherDto.getName());
                    return publisherRepo.save(publisherDTO);
                })
                .orElseGet(() -> {
                    publisherDto.setId(id);
                    return publisherRepo.save(publisherMapper.publisherDTOToPublisher(publisherDto));
                }));
    }

    public void delete(String id) {
        publisherRepo.deleteById(id);
    }
}
