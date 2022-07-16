package Post;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;


public class createUser {
    public static void main(String args[])
    {
        RestAssured.baseURI="https://reqres.in/api/users";
        String userData = "{" +
                "\"name\":\"morpheus\"," +
                "\"job\":\"leader\"" +
                "}";
        given().body("userData")
        .when().post()
                .then().log().all()
                .assertThat()
                .statusCode(201)
                        .body("updatedAt",is(notNullValue()));

    }

}
