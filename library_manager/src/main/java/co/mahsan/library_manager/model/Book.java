package co.mahsan.library_manager.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

import static co.mahsan.library_manager.util.Constant.BOOK_REPO_NAME;

@Document(collection = BOOK_REPO_NAME)
@Data
@Accessors(chain = true)
public class Book {
    @Id
    private String id;
    private String name;
    private String publisherId;
    private List<String> writersIds;

    public Book(String name, String publisherId, List<String> writersIds) {
        this.name = name;
        this.publisherId = publisherId;
        this.writersIds = writersIds;
    }
}
