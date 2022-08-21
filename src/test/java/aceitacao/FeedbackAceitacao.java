package aceitacao;

import aceitacao.dto.feedback.FeedbackReceivedDTO;
import aceitacao.dto.feedback.GivenDTO;
import aceitacao.dto.user.UserDTO;
import aceitacao.service.FeedbackService;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class FeedbackAceitacao {
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    FeedbackService feedbackService = new FeedbackService();

    /*Endpoint: /feedback ------------------------------------------------------------------------------------*/
    @Test
    public void createFeedbackComSucesso() throws IOException {
        //Criando um objeto json
        String jsonBody = lerJson("src/test/resources/feedback.json");
        JSONObject jsonObject = new JSONObject(jsonBody);

        jsonObject.put("message","Ótimo trabalho!");
        jsonObject.put("anonymous",true);
        jsonObject.put("feedbackUserId","1");

        ValidatableResponse res = feedbackService.createFeedback(jsonObject.toString());

        res.statusCode(HttpStatus.SC_OK);

    }

    @Test
    public void createFeedbackUsuarioInexistente() throws IOException {
        //Criando um objeto json
        String jsonBody = lerJson("src/test/resources/feedback.json");
        JSONObject jsonObject = new JSONObject(jsonBody);

        jsonObject.put("message","Ótimo trabalho!");
        jsonObject.put("anonymous",true);
        jsonObject.put("feedbackUserId","20000");

        ValidatableResponse res = feedbackService.createFeedback(jsonObject.toString());

        res.statusCode(HttpStatus.SC_BAD_REQUEST);

    }

    @Test
    public void createFeedbackSemTagComSucesso() throws IOException {
        //Criando um objeto json
        String jsonBody = lerJson("src/test/resources/feedbackSemTag.json");
        JSONObject jsonObject = new JSONObject(jsonBody);

        jsonObject.put("message","Ótimo trabalho!");
        jsonObject.put("anonymous",true);
        jsonObject.put("feedbackUserId","1");

        ValidatableResponse res = feedbackService.createFeedback(jsonObject.toString());

        res.statusCode(HttpStatus.SC_OK);

    }

    @Test
    public void createFeedbackSemMensagem() throws IOException {
        //Criando um objeto json
        String jsonBody = lerJson("src/test/resources/feedback.json");
        JSONObject jsonObject = new JSONObject(jsonBody);

        jsonObject.put("anonymous",true);
        jsonObject.put("feedbackUserId","1");

        ValidatableResponse res = feedbackService.createFeedback(jsonObject.toString());

        res.statusCode(HttpStatus.SC_BAD_REQUEST);

    }

    @Test
    public void createFeedbackSemStatus() throws IOException {
        //Criando um objeto json
        String jsonBody = lerJson("src/test/resources/feedback.json");
        JSONObject jsonObject = new JSONObject(jsonBody);

        jsonObject.put("message","Ótimo trabalho!");
        jsonObject.put("feedbackUserId","1");

        ValidatableResponse res = feedbackService.createFeedback(jsonObject.toString());

        res.statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);

    }

    @Test
    public void createFeedbackSemUser() throws IOException {
        //Criando um objeto json
        String jsonBody = lerJson("src/test/resources/feedback.json");
        JSONObject jsonObject = new JSONObject(jsonBody);

        jsonObject.put("message","Ótimo trabalho!");
        jsonObject.put("anonymous",true);

        ValidatableResponse res = feedbackService.createFeedback(jsonObject.toString());

        res.statusCode(HttpStatus.SC_BAD_REQUEST);

    }

    /*Endpoint: /feedback?idFeedback={}&publico={} ------------------------------------------------------------------------------------*/
    @Test
    public void updateVisibility(){
        ValidatableResponse res = feedbackService.updateVisibility("1",false);

        res.statusCode(HttpStatus.SC_OK);

    }

    @Test
    public void updateVisibilityFeedbackInexistente(){
        ValidatableResponse res = feedbackService.updateVisibility("1000",false);

        res.statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    /*Endpoint: /feedback/receveid ------------------------------------------------------------------------------------*/
    @Test
    public void receivedFeedbacks(){
        FeedbackReceivedDTO[] res = feedbackService.receivedFeebacks();
        //validação por status code na service;
    }

    /*Endpoint: /feedback/receveid-por-id?idUser={} ------------------------------------------------------------------------------------*/
    @Test
    public void receivedFeedbacksById(){
        String id = "11";
        FeedbackReceivedDTO[] res = feedbackService.receivedFeebacksByID(id);

        Assert.assertTrue(res.length == 0 || res[0].getFeedbackUserId().equals(id));
    }

    /*Endpoint: /feedback/gived ------------------------------------------------------------------------------------*/
    @Test
    public void givenFeedbacks(){
        GivenDTO[] res = feedbackService.givenFeebacks();

        //validação por status code na service
    }

    /*Endpoint: /feedback/gived-por-id?idUser={} ------------------------------------------------------------------------------------*/
    @Test
    public void givenFeedbacksByid(){
        String id = "2";
        GivenDTO[] res = feedbackService.givenFeebacksById(id);

        Assert.assertTrue(res.length == 0 || res[0].getUserId().equals(id) );
    }
}
