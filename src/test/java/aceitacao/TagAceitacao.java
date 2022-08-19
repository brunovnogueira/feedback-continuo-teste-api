package aceitacao;

import aceitacao.dto.tag.TagDTO;
import aceitacao.service.FeedbackService;
import aceitacao.service.TagService;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TagAceitacao {
    TagService tagService = new TagService();

    @Test
    public void listTags(){
        TagDTO[] res = tagService.listTag();

        Assert.assertTrue(res.length > 0);
    }
}
