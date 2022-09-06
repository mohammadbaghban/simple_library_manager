package co.mahsan.library_manager.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WriterDto {

    private String id;
    private String name;

}
