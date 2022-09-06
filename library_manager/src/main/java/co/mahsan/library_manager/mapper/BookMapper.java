package co.mahsan.library_manager.mapper;

import co.mahsan.library_manager.model.Book;
import co.mahsan.library_manager.model.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import static co.mahsan.library_manager.util.Constant.SPRING_COMPONENT_MODEL;

@Mapper(componentModel = SPRING_COMPONENT_MODEL)
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDto bookToBookDTO(Book book);

    Book bookDTOToBook(BookDto bookDTO);
}
