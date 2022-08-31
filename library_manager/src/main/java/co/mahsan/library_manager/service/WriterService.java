package co.mahsan.library_manager.service;

import co.mahsan.library_manager.Exceptions.WriterNotFoundException;
import co.mahsan.library_manager.mappers.WriterMapper;
import co.mahsan.library_manager.model.*;
import co.mahsan.library_manager.model.Writer;
import co.mahsan.library_manager.repository.WriterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WriterService {

    private final WriterRepository writerRepo;

    public List<WriterDTO> findAll() {
        List<Writer> writers = writerRepo.findAll();
        List<WriterDTO> writerDTOS = new ArrayList<>();
        for (Writer writer:
                writers) {
            writerDTOS.add(WriterMapper.INSTANCE.writerToWriterDTO(writer));
        }
        return writerDTOS;
    }

    public WriterDTO save(WriterDTO newWriterDTO) {
        Writer newWriter = WriterMapper.INSTANCE.writerDTOToWriter(newWriterDTO);
        if(newWriter != null){
            if (!writerRepo.findByName(newWriter.getName()).isPresent()){
                newWriter = writerRepo.save(newWriter);
            } else {
                newWriter.setId(writerRepo.findByName(newWriter.getName()).get().getId());
            }
        }
        return WriterMapper.INSTANCE.writerToWriterDTO(newWriter);
    }

    public WriterDTO findById(String id) {
        return WriterMapper.INSTANCE.writerToWriterDTO(writerRepo.findById(id)
                .orElseThrow(() -> new WriterNotFoundException(id)));
    }

    public WriterDTO replaceWriter(WriterDTO newWriterDTO, String id) {
        return WriterMapper.INSTANCE.writerToWriterDTO(writerRepo.findById(id)
                .map(writer -> {
                    writer.setName(newWriterDTO.getName());
                    return writerRepo.save(writer);
                })
                .orElseGet(() -> {
                    newWriterDTO.setId(id);
                    return writerRepo.save(WriterMapper.INSTANCE.writerDTOToWriter(newWriterDTO));
                }));
    }

    public void deleteWriterById(String id) {
        writerRepo.deleteById(id);
    }
}
