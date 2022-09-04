package co.mahsan.library_manager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class BookDTO { // todo comment: bad naming. BookDto dorost tare. bia baham sohbat konim.
    @Id // todo comment: @JsonProperty (search kon)
    private String id; // todo comment: baraye id-e DTO, lazeme @Id bezarim?
    private String name;
    private String publisherId;
    private List<String> writersId;

    public BookDTO(){ // todo comment: @NoArgsConstructor YA @Data

    }

    public BookDTO(String name, String publisherId, List<String> writersId) { // todo comment: no usage
        this.name = name;
        this.publisherId = publisherId;
        this.writersId = writersId;
    }

    @Override
    public String toString() {
        return String.format(
                "Book[id:%s, Name:'%s', Writers:'%s', Publisher:'%s']",
                id, name, writersId, publisherId);
    }
}
