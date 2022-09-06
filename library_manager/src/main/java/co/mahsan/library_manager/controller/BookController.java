package co.mahsan.library_manager.controller;

import co.mahsan.library_manager.model.BookDto;
import co.mahsan.library_manager.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @GetMapping("/books")
    List<BookDto> findAll() {
        return service.findAll();
    }

    @PostMapping("/books")
    BookDto save(@RequestBody BookDto bookDto) {
        return service.save(bookDto);
    }

    // Single item

    @GetMapping("/books/{id}")
    BookDto find(@PathVariable String id) {
        return service.findById(id);
    }

    @PutMapping("/books/{id}")
    BookDto update(@RequestBody BookDto bookDto, @PathVariable String id) {
        return service.update(bookDto, id);
    }

    @DeleteMapping("/books/{id}")
    void delete(@PathVariable String id) {
        service.deleteBookById(id);
    }
}
