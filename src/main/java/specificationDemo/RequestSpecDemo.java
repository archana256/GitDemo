package specificationDemo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestSpecDemo {

    public static RequestSpecification getCommonSpec(){
        RequestSpecBuilder builder =new RequestSpecBuilder();
        builder.setBaseUri("https://reqres.in/");
        builder.setBasePath("api/users");

        RequestSpecification requestSpec= builder.build();

        return requestSpec;
    }

    public static void main(String args[]){
     test_withoutParam();
     test_withParam();
    }

    static void test_withoutParam(){
        Response response = RestAssured
                .given()
                        .spec(getCommonSpec())
                        .when()
                        .get();


        response.getBody().prettyPrint();

    }

    static void test_withParam(){
        Response response = RestAssured
                .given()
                .spec(getCommonSpec())
                .queryParam("page","2")
                .when()
                .get();
        response.getBody().prettyPrint();

    }
}
