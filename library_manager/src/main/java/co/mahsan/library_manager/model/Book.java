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
public class Book {
    @Id
    private String id;
    private String name;
    @DBRef
    private Publisher publisher;
    @DBRef
    private List<Writer> writers;

    public Book(String name, Publisher publisher, List<Writer> writers) {
        this.name = name;
        this.publisher = publisher;
        this.writers = writers;
    }

    @Override
    public String toString() {
        return String.format(
                "Book[id:%s, Name:'%s', Writers:'%s', Publisher:'%s']",
                id, name, writers, publisher);
    }
}
