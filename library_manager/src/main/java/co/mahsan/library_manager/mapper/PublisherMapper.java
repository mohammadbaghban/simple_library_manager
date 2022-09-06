package co.mahsan.library_manager.mapper;

import co.mahsan.library_manager.model.Publisher;
import co.mahsan.library_manager.model.PublisherDto;
import org.mapstruct.Mapper;

import static co.mahsan.library_manager.util.Constant.SPRING_COMPONENT_MODEL;

@Mapper(componentModel = SPRING_COMPONENT_MODEL)
public interface PublisherMapper {

    PublisherDto publisherToPublisherDTO(Publisher publisher);

    Publisher publisherDTOToPublisher(PublisherDto publisherDTO);
}
