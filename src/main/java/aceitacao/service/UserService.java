package aceitacao.service;

import aceitacao.dto.UserDTO;
import aceitacao.dto.UserListDTO;
import aceitacao.dto.UserListIdDTO;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.ValidatableResponse;
import java.io.File;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class UserService {
    Random random = new Random();

    String baseUrl = "https://feedback-continuo.herokuapp.com/";

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJmZWVkYmFjay1jb250aW51b3MtYXBpIiwianRpIjoxMTgsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2NjA2NzIxMjIsImV4cCI6MTY2MDc1ODUyMn0.rwT1_X6XMQEiL2duFA0XgVGYjkcNwq7Vq2LLucONHlQ";

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

    public UserListDTO[] listAll(){
        String url = baseUrl+"users/list-all";
        UserListDTO[] res = given()
                .contentType(ContentType.JSON)
                .log().all()
                .header("Authorization",token)
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(UserListDTO[].class)
                ;

        return res;
    }

    public UserListIdDTO listUserById(String id){
        String url = baseUrl+"users/retornar-usuario?id={id}";
        UserListIdDTO res = given()
                .contentType(ContentType.JSON)
                .log().all()
                .header("Authorization",token)
                .pathParam("id",id)
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(UserListIdDTO.class)
                ;

        return res;
    }
}
