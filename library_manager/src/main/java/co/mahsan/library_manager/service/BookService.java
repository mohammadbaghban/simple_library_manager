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
public class BookService { // todo comment: Inversion of Control & Dependency Management

    private final BookRepository bookRepo;
    private final PublisherRepository publisherRepo;

    public List<BookDTO> findAll() {
        List<Book> books = bookRepo.findAll();
        List<BookDTO> bookDTOS = new ArrayList<>(); // todo comment: bad naming
        for (Book book: // todo comment: chera new line?
             books) {
            bookDTOS.add(BookMapper.INSTANCE.bookToBookDTO(book));
        } // todo comment: forEach inja behtar nist?
        return bookDTOS;
    }

    public BookDTO save(BookDTO newBookDTO) { // todo comment: niazi nist takid koni new hastesh
        // todo comment: inconsistent style
        Book newBook = BookMapper.INSTANCE.bookDTOToBook(newBookDTO);
        if(newBook.getPublisherId() != null){
            if (!publisherRepo.findById(newBook.getPublisherId()).isPresent()){ // todo comment: or else throw
                throw new PublisherNotFoundException(newBook.getPublisherId());
            }
        }

        return BookMapper.INSTANCE.bookToBookDTO(bookRepo.save(newBook));
    }

    public BookDTO findById(String id) {
        return BookMapper.INSTANCE.bookToBookDTO(bookRepo.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id)))
                ; // todo comment: bad style
    }

    public BookDTO replaceBook(BookDTO newBookDTO, String id) { // todo comment: stylesh benazaram mitoone behtar bashe
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
