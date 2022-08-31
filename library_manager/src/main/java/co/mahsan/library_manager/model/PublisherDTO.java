package co.mahsan.library_manager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Accessors(chain = true)
public class PublisherDTO {

    @Id
    private String id;
    private String name;

    @Override
    public String toString(){
        return "Name: " + name + " ";
    }
}
