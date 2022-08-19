package aceitacao.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class UserListDTO {
    private String totalElements;
    private String totalPages;
    private String page;
    private String size;
    private ContentDTO[] content;
}
