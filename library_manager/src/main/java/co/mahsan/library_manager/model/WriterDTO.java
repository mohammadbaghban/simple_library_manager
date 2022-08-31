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
public class WriterDTO {

    @Id
    private String id;
    private String name;

    public WriterDTO(){}

    @Override
    public String toString(){
        return "Name: " + name;
    }

}
