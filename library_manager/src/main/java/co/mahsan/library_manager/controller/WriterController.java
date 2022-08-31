package co.mahsan.library_manager.controller;


import co.mahsan.library_manager.model.Writer;
import co.mahsan.library_manager.service.WriterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WriterController {
    private final WriterService service;

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/writers")
    List<Writer> all() {
        return service.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/writers")
    Writer newWriter(@RequestBody Writer newWriter) {
        return service.save(newWriter);
    }

    // Single item

    @GetMapping("/writers/{id}")
    Writer one(@PathVariable String id) {

        return service.findById(id);
    }

    @PutMapping("/writers/{id}")
    Writer replaceWriter(@RequestBody Writer newWriter, @PathVariable String id) {
        return service.replaceWriter(newWriter, id);
    }

    @DeleteMapping("/writers/{id}")
    void deleteWriter(@PathVariable String id) {
        service.deleteWriterById(id);
    }
}

