package co.mahsan.library_manager.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Writer {

    @Id
    private String id;
    private String firstName;
    private String lastName;

    public Writer(){}

    public Writer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString(){
        return "Name: " + firstName + " " + lastName;
    }

}
