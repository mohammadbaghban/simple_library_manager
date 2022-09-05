package co.mahsan.library_manager.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PublisherDTO { //todo comment: PublisherDto

    private String id;
    private String name;
}
