package aceitacao.service;

import aceitacao.dto.feedback.FeedbackReceivedDTO;
import aceitacao.dto.feedback.GivenDTO;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class FeedbackService {
    String baseUrl = "https://feedback-continuos.herokuapp.com";

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJmZWVkYmFjay1jb250aW51b3MtYXBpIiwianRpIjoyLCJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNjYxMTg2OTcxLCJleHAiOjE2NjEyNzMzNzF9.XScX1MFKuCci06-YmfSKI6RNcrQ3u89EH4X6yoBuhdg";
//    eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJmZWVkYmFjay1jb250aW51b3MtYXBpIiwianRpIjoxMSwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTY2MDkzNDAwNiwiZXhwIjoxNjYxMDIwNDA2fQ.oqLxpB_6UcQybhKYKXwWO5KOqk8paJ-mk_uhkiGyJGY
    /*FEEDBACK-------------------------------------------------------------------------------------------------------*/
    public ValidatableResponse createFeedback(String jsonBody){
        String url = baseUrl+"/feedback";
        ValidatableResponse res = given()
                .contentType(ContentType.JSON)
                .log().all()
                .header("Authorization",token)
                .body(jsonBody)
                .when()
                .post(url)
                .then()
                .log().all()
                ;
        return res;
    }

    public ValidatableResponse updateVisibility(String id,boolean publico){
        String url = baseUrl+"/feedback?idFeedback={id}&publico={publico}";
        ValidatableResponse res = given()
                .contentType(ContentType.JSON)
                .log().all()
                .header("Authorization",token)
                .pathParam("id",id)
                .pathParam("publico",publico)
                .when()
                .put(url)
                .then()
                .log().all()
                ;
        return res;
    }

    /*GETS-------------------------------------------------------------------------------------------------------*/
    public FeedbackReceivedDTO[] receivedFeebacks(){
        String url = baseUrl+"/feedback/receveid";
        FeedbackReceivedDTO[] res = given()
                .contentType(ContentType.JSON)
                .log().all()
                .header("Authorization",token)
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(FeedbackReceivedDTO[].class)
                ;
        return res;
    }

    public FeedbackReceivedDTO[] receivedFeebacksByID(String id){
        String url = baseUrl+"/feedback/receveid-por-id?idUser={id}";
        FeedbackReceivedDTO[] res = given()
                .contentType(ContentType.JSON)
                .log().all()
                .header("Authorization",token)
                .pathParam("id",id)
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(FeedbackReceivedDTO[].class)
                ;
        return res;
    }

    public GivenDTO[] givenFeebacks(){
        String url = baseUrl+"/feedback/gived";
        GivenDTO[] res = given()
                .contentType(ContentType.JSON)
                .log().all()
                .header("Authorization",token)
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(GivenDTO[].class)
                ;
        return res;
    }

    public GivenDTO[] givenFeebacksById(String id){
        String url = baseUrl+"/feedback/gived-por-id?idUser={id}";
        GivenDTO[] res = given()
                .contentType(ContentType.JSON)
                .log().all()
                .header("Authorization",token)
                .pathParam("id",id)
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(GivenDTO[].class)
                ;
        return res;
    }
}
