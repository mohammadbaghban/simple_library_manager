package co.mahsan.library_manager.controller;

import co.mahsan.library_manager.model.WriterDto;
import co.mahsan.library_manager.service.WriterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WriterController {
    private final WriterService service;
    @GetMapping("/writers")
    List<WriterDto> findAll() {
        return service.findAll();
    }

    @PostMapping("/writers")
    WriterDto save(@RequestBody WriterDto newWriter) {
        return service.save(newWriter);
    }

    // Single item

    @GetMapping("/writers/{id}")
    WriterDto find(@PathVariable String id) {

        return service.findById(id);
    }

    @PutMapping("/writers/{id}")
    WriterDto update(@RequestBody WriterDto writerDTO, @PathVariable String id) {
        return service.update(writerDTO, id);
    }

    @DeleteMapping("/writers/{id}")
    void delete(@PathVariable String id) {
        service.delete(id);
    }
}

