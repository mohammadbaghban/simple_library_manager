package co.mahsan.library_manager.controller;

import co.mahsan.library_manager.model.Publisher;
import co.mahsan.library_manager.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService service;

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/publishers")
    List<Publisher> all() {
        return service.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/publishers")
    Publisher newPublisher(@RequestBody Publisher newPublisher) {
        return service.save(newPublisher);
    }

    // Single item

    @GetMapping("/publishers/{id}")
    Publisher one(@PathVariable String id) {

        return service.findById(id);
    }

    @PutMapping("/publishers/{id}")
    Publisher replacePublisher(@RequestBody Publisher newPublisher, @PathVariable String id) {
        return service.replacePublisher(newPublisher, id);
    }

    @DeleteMapping("/publishers/{id}")
    void deletePublisher(@PathVariable String id) {
        service.deletePublisherById(id);
    }
}
