package co.mahsan.library_manager.controller;

import co.mahsan.library_manager.model.PublisherDto;
import co.mahsan.library_manager.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService service;

    @GetMapping("/publishers")
    List<PublisherDto> findAll() {
        return service.findAll();
    }

    @PostMapping("/publishers")
    PublisherDto save(@RequestBody PublisherDto newPublisher) {
        return service.save(newPublisher);
    }

    @GetMapping("/publishers/{id}")
    PublisherDto find(@PathVariable String id) {

        return service.findById(id);
    }

    @PutMapping("/publishers/{id}")
    PublisherDto update(@RequestBody PublisherDto newPublisher, @PathVariable String id) {
        return service.update(newPublisher, id);
    }

    @DeleteMapping("/publishers/{id}")
    void delete(@PathVariable String id) {
        service.delete(id);
    }
}
