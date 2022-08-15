package aceitacao.service;

import aceitacao.dto.UserDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class UserService {
    Random random = new Random();

    String baseUrl = "https://feedback-continuo.herokuapp.com/";

    String token = ""; //TODO: Substituir

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

    public UserDTO listar(){
        return new UserDTO();
    }

    public UserDTO atualizar(){
        return new UserDTO();
    }

    public void deletar(){

    }
}
