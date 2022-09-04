package co.mahsan.library_manager.service;

import co.mahsan.library_manager.exception.BookNotFoundException;
import co.mahsan.library_manager.mapper.BookMapper;
import co.mahsan.library_manager.model.Book;
import co.mahsan.library_manager.model.BookDto;
import co.mahsan.library_manager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepo;

    public List<BookDto> findAll() {
        return bookRepo.findAll().stream().map(BookMapper.INSTANCE::bookToBookDTO).collect(Collectors.toList());
    }

    public BookDto save(BookDto bookDto) {
        Book newBook = BookMapper.INSTANCE.bookDTOToBook(bookDto);
        return BookMapper.INSTANCE.bookToBookDTO(bookRepo.save(newBook));
    }

    public BookDto findById(String id) {
        return BookMapper.INSTANCE.bookToBookDTO(bookRepo.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id)));
    }

    public BookDto replaceBook(BookDto newBookDto, String id) {
        return BookMapper.INSTANCE.bookToBookDTO(bookRepo.findById(id)
                .map(book -> {
                    book.setName(newBookDto.getName());
                    book.setPublisherId(newBookDto.getPublisherId());
                    book.setWritersIds(newBookDto.getWritersId());
                    return bookRepo.save(book);
                })
                .orElseGet(() -> {
                    newBookDto.setId(id);
                    return bookRepo.save(BookMapper.INSTANCE.bookDTOToBook(newBookDto));
                }));
    }

    public void deleteBookById(String id) {
        bookRepo.deleteById(id);
    }
}
