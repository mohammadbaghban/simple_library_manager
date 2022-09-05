package co.mahsan.library_manager.controller;

import co.mahsan.library_manager.model.PublisherDTO;
import co.mahsan.library_manager.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PublisherController {//todo comment: todo-comment haye BookController
    private final PublisherService service;

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/publishers")
    List<PublisherDTO> all() {
        return service.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/publishers")
    PublisherDTO newPublisher(@RequestBody PublisherDTO newPublisher) {
        return service.save(newPublisher);
    }

    // Single item

    @GetMapping("/publishers/{id}")
    PublisherDTO one(@PathVariable String id) {

        return service.findById(id);
    }

    @PutMapping("/publishers/{id}")
    PublisherDTO replacePublisher(@RequestBody PublisherDTO newPublisher, @PathVariable String id) {
        return service.replacePublisher(newPublisher, id);
    }

    @DeleteMapping("/publishers/{id}")
    void deletePublisher(@PathVariable String id) {
        service.deletePublisherById(id);
    }
}
