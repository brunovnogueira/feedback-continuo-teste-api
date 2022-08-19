package aceitacao.service;

import aceitacao.dto.user.UserDTO;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class FeedbackService {
    String baseUrl = "https://feedback-continuos.herokuapp.com";

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJmZWVkYmFjay1jb250aW51b3MtYXBpIiwianRpIjoxMSwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTY2MDkzNDAwNiwiZXhwIjoxNjYxMDIwNDA2fQ.oqLxpB_6UcQybhKYKXwWO5KOqk8paJ-mk_uhkiGyJGY";

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

}