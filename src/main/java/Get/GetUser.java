package Get;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetUser {

    public static void main(String args[])
    {
        RestAssured.baseURI="https://reqres.in/api/users";
        given().
                queryParam("page","2")
                .body("")
                .when().
                get()
                .then().
                assertThat().statusCode(200)
                .body("page",equalTo(2));

    }
}
