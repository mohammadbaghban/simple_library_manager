package co.mahsan.library_manager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "writer")
@Getter
@Setter
@Accessors(chain = true)
public class Writer {

    @Id
    private String id;
    private String name;

    public Writer(){} // todo comment: @NoArgsConstructor

    @Override
    public String toString(){
        return "Name: " + name;
    }

}
