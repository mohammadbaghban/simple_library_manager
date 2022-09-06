package co.mahsan.library_manager.service;

import co.mahsan.library_manager.util.exception.BookNotFoundException;
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
    private final BookMapper bookMapper;

    public List<BookDto> findAll() {
        return bookRepo.findAll().stream().map(bookMapper::bookToBookDTO).collect(Collectors.toList());
    }

    public BookDto save(BookDto bookDto) {
        Book newBook = bookMapper.bookDTOToBook(bookDto);
        return bookMapper.bookToBookDTO(bookRepo.save(newBook));
    }

    public BookDto findById(String id) {
        return bookMapper.bookToBookDTO(bookRepo.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id)));
    }

    public BookDto update(BookDto newBookDto, String id) {
        return bookMapper.bookToBookDTO(bookRepo.findById(id)
                .map(book -> {
                    book.setName(newBookDto.getName());
                    book.setPublisherId(newBookDto.getPublisherId());
                    book.setWritersIds(newBookDto.getWritersId());
                    return bookRepo.save(book);
                })
                .orElseGet(() -> {
                    newBookDto.setId(id);
                    return bookRepo.save(bookMapper.bookDTOToBook(newBookDto));
                }));
    }

    public void deleteBookById(String id) {
        bookRepo.deleteById(id);
    }
}
