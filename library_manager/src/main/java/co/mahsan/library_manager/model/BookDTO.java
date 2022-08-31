package co.mahsan.library_manager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "book")
@Getter
@Setter
@Accessors(chain = true)
public class BookDTO {
    @Id
    private String id;
    private String name;
    @DBRef
    private String publisherId;
    @DBRef
    private List<String> writersId;

    public BookDTO(String name, String publisherId, List<String> writersId) {
        this.name = name;
        this.publisherId = publisherId;
        this.writersId = writersId;
    }

    @Override
    public String toString() {
        return String.format(
                "Book[id:%s, Name:'%s', Writers:'%s', Publisher:'%s']",
                id, name, writersId, publisherId);
    }
}
