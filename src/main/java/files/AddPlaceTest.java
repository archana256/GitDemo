package files;

import io.restassured.RestAssured;
import pojo.AddPlaceAPI;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class AddPlaceTest {
    public static void main(String args[])
    {
       // create object of main pojo class
        AddPlaceAPI AP= new AddPlaceAPI();
        AP.setAccuracy(50);
        AP.setName("Frontline house Two");
        AP.setPhone_number("(+91) 983 893 3937");
        AP.setAddress("29, side layout, cohen 09");
        AP.setWebsite("http://google.com");
        AP.setLanguage("French-IN");
        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");
        AP.setTypes(myList);
        //create object of location class
        Location lc=new Location();
        lc.setLat(-39.383494);
        lc.setLng(33.427362);
        AP.setLocation(lc);




        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response =given().log().all().queryParams("key","qaclick123")
                .body(AP)
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).extract().response().asString();
    }
}
