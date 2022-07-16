package files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class AddBook {

    @Test(dataProvider = "BookData",priority = 1)
    public void addBook(String isbn,String aisle,String author){
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response =given().log().all().header("Content-Type","application/json")
                .body(payload.addBook(isbn,aisle,author))
                .when().post("Library/Addbook.php").
                then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath js = ReusableMethods.rawToJson(response);
        String id=js.getString("ID");
        System.out.println(id);

        //delete generated book

        if (id.equalsIgnoreCase(id)) {
            RestAssured.baseURI = "https://rahulshettyacademy.com";
            given().log().all().header("Content-Type", "application/json")
                    .body(payload.deleteBook(id))
                    .when().delete("Library/DeleteBook.php")
                    .then().log().all().assertThat().statusCode(200);


        }

    }





    @DataProvider(name="BookData")
    //array - collection of elements
    //multi dimension arrays - collection of arrays
    public Object[][] getData(){
       return new Object[][]{{"test12","123","Archana"},{"test2","7865","archana123"},{"test3","2567","archana786"}};
    }

}
