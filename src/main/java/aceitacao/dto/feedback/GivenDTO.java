package aceitacao.dto.feedback;

import aceitacao.dto.tag.TagDTO;
import aceitacao.dto.user.FeedbacksGivenDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class GivenDTO {
    private String message;
    private boolean anonymous;
    private boolean publico;
    private FeedbacksGivenDTO feedbackEntityReceived;
    private TagDTO[] tagsList;
    private String idFeedback;
    private String userId;
    private String dataEHora;
}
