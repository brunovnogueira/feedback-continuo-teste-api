package aceitacao.dto.tag;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class TagDTO {
    private String name;
    private String idTag;
}
