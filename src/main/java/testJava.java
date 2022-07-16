import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class testJava {

@BeforeSuite
    void setUp(){
    System.out.println("setup");
}

@Test
    void get_req(){
    RestAssured.baseURI ="http://localhost:3000/";
    RestAssured.basePath="posts";
    given().when().get().then().log().all();
}

void tearDown(){
    System.out.println("tear down");
}
}
