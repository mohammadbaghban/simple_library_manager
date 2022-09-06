package co.mahsan.library_manager.mapper;

import co.mahsan.library_manager.model.Writer;
import co.mahsan.library_manager.model.WriterDto;
import org.mapstruct.Mapper;

import static co.mahsan.library_manager.util.Constant.SPRING_COMPONENT_MODEL;

@Mapper(componentModel = SPRING_COMPONENT_MODEL)
public interface WriterMapper {

    WriterDto writerToWriterDTO(Writer writer);

    Writer writerDTOToWriter(WriterDto writerDTO);
}
