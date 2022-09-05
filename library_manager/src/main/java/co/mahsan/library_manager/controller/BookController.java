package co.mahsan.library_manager.controller;

import java.util.List;

import co.mahsan.library_manager.model.BookDto;
import co.mahsan.library_manager.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService service;
    //todo comment: in comment ha baraye chie?
    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/books")
    List<BookDto> all() { //todo comment: findAll OR getAll
        return service.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/books")
    BookDto newBook(@RequestBody BookDto newBook) {//todo comment: 1. method name: save  2. var name: bookDto
        return service.save(newBook);
    }

    // Single item

    @GetMapping("/books/{id}")
    BookDto one(@PathVariable String id) {//todo comment: findById OR getById OR find OR get

        return service.findById(id);
    }

    @PutMapping("/books/{id}")
    BookDto replaceBook(@RequestBody BookDto newBook, @PathVariable String id) {//todo comment: 1. method name: update  2. var name: bookDto
        return service.replaceBook(newBook, id);
    }

    @DeleteMapping("/books/{id}")
    void deleteBook(@PathVariable String id) {//todo comment: method name: delete OR deleteById
        service.deleteBookById(id);
    }
}
