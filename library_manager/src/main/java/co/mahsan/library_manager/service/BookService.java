package co.mahsan.library_manager.service;

import co.mahsan.library_manager.Exceptions.BookNotFoundException;
import co.mahsan.library_manager.Exceptions.PublisherNotFoundException;
import co.mahsan.library_manager.mappers.BookMapper;
import co.mahsan.library_manager.model.Book;
import co.mahsan.library_manager.model.BookDTO;
import co.mahsan.library_manager.repository.BookRepository;
import co.mahsan.library_manager.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepo;
    private final PublisherRepository publisherRepo;

    public List<BookDTO> findAll() {
        List<Book> books = bookRepo.findAll();
        List<BookDTO> bookDTOS = new ArrayList<>();
        for (Book book:
             books) {
            bookDTOS.add(BookMapper.INSTANCE.bookToBookDTO(book));
        }
        return bookDTOS;
    }

    public BookDTO save(BookDTO newBookDTO) {

        Book newBook = BookMapper.INSTANCE.bookDTOToBook(newBookDTO);
        if(newBook.getPublisherId() != null){
            if (!publisherRepo.findById(newBook.getPublisherId()).isPresent()){
                throw new PublisherNotFoundException(newBook.getPublisherId());
            }
        }

        return BookMapper.INSTANCE.bookToBookDTO(bookRepo.save(newBook));
    }

    public BookDTO findById(String id) {
        return BookMapper.INSTANCE.bookToBookDTO(bookRepo.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id)))
                ;
    }

    public BookDTO replaceBook(BookDTO newBookDTO, String id) {
        return BookMapper.INSTANCE.bookToBookDTO(bookRepo.findById(id)
                .map(book -> {
                    book.setName(newBookDTO.getName());
                    book.setPublisherId(newBookDTO.getPublisherId());
                    book.setWritersId(newBookDTO.getWritersId());
                    return bookRepo.save(book);
                })
                .orElseGet(() -> {
                    newBookDTO.setId(id);
                    return bookRepo.save(BookMapper.INSTANCE.bookDTOToBook(newBookDTO));
                }));
    }

    public void deleteBookById(String id) {
        bookRepo.deleteById(id);
    }
}
