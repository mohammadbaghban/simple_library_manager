package co.mahsan.library_manager.service;

import co.mahsan.library_manager.util.exception.WriterNotFoundException;
import co.mahsan.library_manager.mapper.WriterMapper;
import co.mahsan.library_manager.model.Writer;
import co.mahsan.library_manager.model.WriterDto;
import co.mahsan.library_manager.repository.WriterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WriterService {

    private final WriterRepository writerRepo;
    private final WriterMapper writerMapper;

    public List<WriterDto> findAll() {
        return writerRepo.findAll().stream().map(writerMapper::writerToWriterDTO).collect(Collectors.toList());
    }

    public WriterDto save(WriterDto newWriterDto) {
        Writer newWriter =  writerRepo.save(writerMapper.writerDTOToWriter(newWriterDto));
        return writerMapper.writerToWriterDTO(newWriter);
    }

    public WriterDto findById(String id) {
        return writerMapper.writerToWriterDTO(writerRepo.findById(id)
                .orElseThrow(() -> new WriterNotFoundException(id)));
    }

    public WriterDto update(WriterDto newWriterDto, String id) {
        return writerMapper.writerToWriterDTO(writerRepo.findById(id)
                .map(writer -> {
                    writer.setName(newWriterDto.getName());
                    return writerRepo.save(writer);
                })
                .orElseGet(() -> {
                    newWriterDto.setId(id);
                    return writerRepo.save(writerMapper.writerDTOToWriter(newWriterDto));
                }));
    }

    public void delete(String id) {
        writerRepo.deleteById(id);
    }
}
