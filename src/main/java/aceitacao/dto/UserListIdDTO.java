package aceitacao.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class UserListIdDTO {
    private String idUser;
    private String userNamer;
    private String userRole;
    private String email;
    private String avatar;
}
