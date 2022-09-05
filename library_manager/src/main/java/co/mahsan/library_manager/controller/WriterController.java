package co.mahsan.library_manager.controller;

import co.mahsan.library_manager.model.WriterDTO;
import co.mahsan.library_manager.service.WriterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WriterController { //todo comment: todo-comment haye BookController
    private final WriterService service;

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/writers")
    List<WriterDTO> all() {
        return service.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/writers")
    WriterDTO newWriter(@RequestBody WriterDTO newWriter) {
        return service.save(newWriter);
    }

    // Single item

    @GetMapping("/writers/{id}")
    WriterDTO one(@PathVariable String id) {

        return service.findById(id);
    }

    @PutMapping("/writers/{id}")
    WriterDTO replaceWriter(@RequestBody WriterDTO newWriter, @PathVariable String id) {
        return service.replaceWriter(newWriter, id);
    }

    @DeleteMapping("/writers/{id}")
    void deleteWriter(@PathVariable String id) {
        service.deleteWriterById(id);
    }
}

