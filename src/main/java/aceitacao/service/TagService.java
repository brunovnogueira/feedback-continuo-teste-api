package aceitacao.service;

import aceitacao.dto.tag.TagDTO;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class TagService {
    String baseUrl = "https://feedback-continuos.herokuapp.com";

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJmZWVkYmFjay1jb250aW51b3MtYXBpIiwianRpIjoyLCJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNjYxMTg2OTcxLCJleHAiOjE2NjEyNzMzNzF9.XScX1MFKuCci06-YmfSKI6RNcrQ3u89EH4X6yoBuhdg";
//    eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJmZWVkYmFjay1jb250aW51b3MtYXBpIiwianRpIjoxMSwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTY2MDkzNDAwNiwiZXhwIjoxNjYxMDIwNDA2fQ.oqLxpB_6UcQybhKYKXwWO5KOqk8paJ-mk_uhkiGyJGY
    /*TAG-------------------------------------------------------------------------------------------------------*/
    public TagDTO[] listTag(){
        String url = baseUrl+"/tag";
        TagDTO[] res = given()
                .contentType(ContentType.JSON)
                .log().all()
                .header("Authorization",token)
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(TagDTO[].class)
                ;
        return res;
    }
}
