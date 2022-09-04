package co.mahsan.library_manager.mappers; // todo comment: behtare esme package mofrad bashe

import co.mahsan.library_manager.model.Book;
import co.mahsan.library_manager.model.BookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDTO bookToBookDTO(Book book);
    Book bookDTOToBook(BookDTO bookDTO);

}
