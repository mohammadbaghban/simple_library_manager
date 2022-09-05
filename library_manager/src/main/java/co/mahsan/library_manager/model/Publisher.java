package co.mahsan.library_manager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static co.mahsan.library_manager.util.Constant.PUBLISHER_REPO_NAME;

@Document(collection = PUBLISHER_REPO_NAME)
@Getter
@Setter
@Accessors(chain = true)
public class Publisher {

    @Id
    private String id;
    private String name;

    @Override
    public String toString(){ //todo comment: mage gharar nashod @Data bezani?
        return "Name: " + name + " ";
    }
}
