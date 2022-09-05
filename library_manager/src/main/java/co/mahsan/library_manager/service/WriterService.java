package co.mahsan.library_manager.service;

import co.mahsan.library_manager.exception.WriterNotFoundException;
import co.mahsan.library_manager.mapper.WriterMapper;
import co.mahsan.library_manager.model.Writer;
import co.mahsan.library_manager.model.WriterDTO;
import co.mahsan.library_manager.repository.WriterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WriterService {

    private final WriterRepository writerRepo;

    public List<WriterDTO> findAll() {//todo comment: stream
        List<Writer> writers = writerRepo.findAll();
        List<WriterDTO> writerDTOS = new ArrayList<>();
        for (Writer writer :
                writers) {
            writerDTOS.add(WriterMapper.INSTANCE.writerToWriterDTO(writer));
        }
        return writerDTOS;
    }

    public WriterDTO save(WriterDTO newWriterDTO) {
        Writer newWriter = WriterMapper.INSTANCE.writerDTOToWriter(newWriterDTO);
        newWriter = writerRepo.save(newWriter);
        //todo comment: code bala khoob nist. in behtar nist? -> Writer newWriter =  writerRepo.save(WriterMapper.INSTANCE.writerDTOToWriter(newWriterDTO));
        return WriterMapper.INSTANCE.writerToWriterDTO(newWriter);
    }

    public WriterDTO findById(String id) {
        return WriterMapper.INSTANCE.writerToWriterDTO(writerRepo.findById(id)
                .orElseThrow(() -> new WriterNotFoundException(id)));
    }

    public WriterDTO replaceWriter(WriterDTO newWriterDTO, String id) {//todo comment: Dto
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
