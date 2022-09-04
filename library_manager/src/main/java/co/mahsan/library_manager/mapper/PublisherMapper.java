package co.mahsan.library_manager.mapper;

import co.mahsan.library_manager.model.Publisher;
import co.mahsan.library_manager.model.PublisherDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PublisherMapper {
    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);

    PublisherDTO publisherToPublisherDTO(Publisher publisher);
    Publisher publisherDTOToPublisher(PublisherDTO publisherDTO);
}
