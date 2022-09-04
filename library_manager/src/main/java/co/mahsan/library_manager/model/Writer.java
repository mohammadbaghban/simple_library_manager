package co.mahsan.library_manager.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static co.mahsan.library_manager.util.Constant.WRITER_REPO_NAME;

@Document(collection = WRITER_REPO_NAME)
@Data
@Accessors(chain = true)
public class Writer {

    @Id
    private String id;
    private String name;
}
