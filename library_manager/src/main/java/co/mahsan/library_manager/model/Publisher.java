package co.mahsan.library_manager.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Publisher {

    @Id
    private String id;
    private String name;

    public Publisher(){}

    public Publisher(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "Name: " + name + " ";
    }
}
