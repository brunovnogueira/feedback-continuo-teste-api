package aceitacao.dto.feedback;

import aceitacao.dto.tag.TagDTO;
import aceitacao.dto.user.FeedbacksGivenDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class FeedbackReceivedDTO {
    private String message;
    private boolean anonymous;
    private boolean publico;
    private String feedbackUserId;
    private TagDTO[] tagsList;
    private String idFeedback;
    private FeedbacksGivenDTO feedbacksGiven;
    private String dataEHora;
}
