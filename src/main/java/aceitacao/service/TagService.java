package aceitacao.service;

import aceitacao.dto.tag.TagDTO;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class TagService {
    String baseUrl = "https://feedback-continuo.herokuapp.com";

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJmZWVkYmFjay1jb250aW51b3MtYXBpIiwianRpIjoxMSwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTY2MDkzNDAwNiwiZXhwIjoxNjYxMDIwNDA2fQ.oqLxpB_6UcQybhKYKXwWO5KOqk8paJ-mk_uhkiGyJGY";

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
