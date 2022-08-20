package aceitacao.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class FeedbacksGivenDTO {
    private String idUser;
    private String name;
    private String userRole;
    private String avatar;
}
