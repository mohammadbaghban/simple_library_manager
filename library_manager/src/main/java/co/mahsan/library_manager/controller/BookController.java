package co.mahsan.library_manager.controller;

import java.util.List;

import co.mahsan.library_manager.model.Book;
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
class BookController {

    private final BookService service;

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/books")
    List<Book> all() {
        return service.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/books")
    Book newBook(@RequestBody Book newBook) {
        return service.save(newBook);
    }

    // Single item

    @GetMapping("/books/{id}")
    Book one(@PathVariable String id) {

        return service.findById(id);
    }

    @PutMapping("/books/{id}")
    Book replaceBook(@RequestBody Book newBook, @PathVariable String id) {
        return service.replaceBook(newBook, id);
    }

    @DeleteMapping("/books/{id}")
    void deleteBook(@PathVariable String id) {
        service.deleteBookById(id);
    }
}
