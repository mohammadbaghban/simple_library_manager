package co.mahsan.library_manager.model;//todo comment: class haye DTO bayad to package controller bashand. bia ba ham sohbat konim

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
    //todo comment: inja behtare new line nabashe :D
}
