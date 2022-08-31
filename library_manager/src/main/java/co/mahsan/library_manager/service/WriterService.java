package co.mahsan.library_manager.service;

import co.mahsan.library_manager.Exceptions.WriterNotFoundException;
import co.mahsan.library_manager.model.Writer;
import co.mahsan.library_manager.model.Writer;
import co.mahsan.library_manager.repository.BookRepository;
import co.mahsan.library_manager.repository.WriterRepository;
import co.mahsan.library_manager.repository.WriterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WriterService {

    private final BookRepository bookRepo;
    private final WriterRepository writerRepo;

    public List<Writer> findAll() {
        return writerRepo.findAll();
    }

    public Writer save(Writer newWriter) {
        if(newWriter != null){
            if (writerRepo.findByName(newWriter.getName()).isEmpty()){
                newWriter = writerRepo.save(newWriter);
            } else {
                newWriter.setId(writerRepo.findByName(newWriter.getName()).get().getId());
            }
        }
        return newWriter;
    }

    public Writer findById(String id) {
        return writerRepo.findById(id)
                .orElseThrow(() -> new WriterNotFoundException(id));
    }

    public Writer replaceWriter(Writer newWriter, String id) {
        return writerRepo.findById(id)
                .map(writer -> {
                    writer.setName(newWriter.getName());
                    return writerRepo.save(writer);
                })
                .orElseGet(() -> {
                    newWriter.setId(id);
                    return writerRepo.save(newWriter);
                });
    }

    public void deleteWriterById(String id) {
        writerRepo.deleteById(id);
    }
}
