package co.mahsan.library_manager.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WriterDTO { //todo comment: WriterDto

    private String id;
    private String name;

    public WriterDTO() { //todo comment: 1.chera PublisherDto ino nadare? 2.@NoArgsConstructor bezan age ino lazem dari
    }
}
