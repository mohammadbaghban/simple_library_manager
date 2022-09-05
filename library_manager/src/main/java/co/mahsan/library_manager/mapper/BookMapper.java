package co.mahsan.library_manager.mapper;

import co.mahsan.library_manager.model.Book;
import co.mahsan.library_manager.model.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDto bookToBookDTO(Book book); //todo comment: beyne method ha bayad new line bashe
    Book bookDTOToBook(BookDto bookDTO);
    //todo comment: inja behtare new line nabashe :D
}
//todo comment: style baghie mapper ha ro ham dorost kon