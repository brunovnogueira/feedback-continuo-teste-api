package aceitacao.service;

import aceitacao.dto.UserDTO;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.ValidatableResponse;
import java.io.File;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class UserService {
    Random random = new Random();

    String baseUrl = "https://feedback-continuo.herokuapp.com/";

    String token = ""; //TODO: Substituir

    /*CREATE-------------------------------------------------------------------------------------------------------*/
    public UserDTO create(String jsonBody){
        String url = baseUrl+"users/create";
        UserDTO res = given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
        .when()
                .post(url)
        .then()
                .log().all()
                .statusCode(200)
                .extract().as(UserDTO.class)
        ;
        return res;
    }

    public ValidatableResponse createBadRequest(String jsonBody){
        String url = baseUrl+"users/create";
         ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
        .when()
                .post(url)
        .then()
                .log().all()
        ;
         return response;
    }

    /*FILE UPDATE-------------------------------------------------------------------------------------------------------*/
    public ValidatableResponse updateFile(String id){
        String url = baseUrl+"users/update-file";
        ValidatableResponse res = given()
                .header(new Header("content-type", "multipart/form-data"))
                .multiPart(new File("src/test/resources/images.png"))
                .log().all()
                .formParam("id",id)
        .when()
                .put(url)
        .then()
                .log().all();

        return res;
    }

    public ValidatableResponse updateFileTxt(String id){
        String url = baseUrl+"users/update-file";
        ValidatableResponse res = given()
                .header(new Header("content-type", "multipart/form-data"))
                .multiPart(new File("src/test/resources/texto.txt"))
                .log().all()
                .formParam("id",id)
        .when()
                .put(url)
        .then()
                .log().all();

        return res;
    }

    public ValidatableResponse updateFileEmpty(String id){
        String url = baseUrl+"users/update-file";
        ValidatableResponse res = given()
                .header(new Header("content-type", "multipart/form-data"))
                .log().all()
                .formParam("id",id)
        .when()
                .put(url)
        .then()
                .log().all();

        return res;
    }

    public ValidatableResponse userLogin(String jsonBody){
        String url = baseUrl+"users/login";
        ValidatableResponse res = given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .post(url)
                .then()
                .log().all()
                ;

        return res;
    }
}
