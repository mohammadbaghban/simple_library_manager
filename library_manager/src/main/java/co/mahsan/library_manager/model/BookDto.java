package co.mahsan.library_manager.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class BookDto {
    private String id;
    private String name;
    private String publisherId;
    private List<String> writersId;
}
