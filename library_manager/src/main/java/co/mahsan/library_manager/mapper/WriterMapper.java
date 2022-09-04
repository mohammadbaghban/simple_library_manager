package co.mahsan.library_manager.mapper;

import co.mahsan.library_manager.model.Writer;
import co.mahsan.library_manager.model.WriterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WriterMapper {
    WriterMapper INSTANCE = Mappers.getMapper(WriterMapper.class);
    WriterDTO writerToWriterDTO(Writer writer);
    Writer writerDTOToWriter(WriterDTO writerDTO);
}
