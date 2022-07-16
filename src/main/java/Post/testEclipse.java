package Post;

import files.ReusableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class testEclipse {

    public static void main(String args[]) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
       String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(payload.addPlace())
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.41 (Ubuntu)")
                .extract().response().asString();
           System.out.println(response);

           //for parsing json
        JsonPath js = new JsonPath(response);
        String PlaceId = js.getString("place_id");
        System.out.println("Place Id is" +
                PlaceId);

        //update address

        String updateAddress = "Summer walk , Africa";
        given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\""+PlaceId+"\",\n" +
                        "\"address\":\""+updateAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));


        //get place

        String getResponse= given().log().all().queryParam("key","qaclick123")
                .queryParam("place_id",PlaceId)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();

        JsonPath js1= ReusableMethods.rawToJson(getResponse);
        String actualAddress= js1.getString("address");
        System.out.println(actualAddress);

        //for assertions use either testng or junit method since java donot have any and we are out of rest assured loop
        Assert.assertEquals(actualAddress,updateAddress);
    }
    }
