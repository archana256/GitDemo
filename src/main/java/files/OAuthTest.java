package files;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import pojo.Api;
import pojo.GetCourse;
import pojo.WebAutomation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;

public class OAuthTest {
    public static void main(String args[])throws IOException {

//To get code from authorization server
String[] courseTitles= {"Selenium Webdriver Java","Cypress","Protractor"};
String url= "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWjwUAki9sIzuK8Dm1Pc5zGOQVMVMXELmUxpt0gh5zcpfQg_WQ_YPZNJqWJy9inzNQ&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
        String tempUrl= url.split("code=")[1];
       String code= tempUrl.split("&scope")[0];
       System.out.println(code);


        //To get access token from access token url
       String accessTokenResponse = given().log().all().urlEncodingEnabled(false)
                .queryParams("code",code)
                .queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
                .queryParams("grant_type","authorization_code")
                .when().post("https://www.googleapis.com/oauth2/v4/token")
               .then().log().all().extract().response().asString();


        JsonPath js = new JsonPath(accessTokenResponse);
       String accessToken=  js.getString("access_token");
       System.out.println(accessToken);



        //To access the main url
        GetCourse gc= given().log().all().queryParams("access_token",accessToken)
                .expect().defaultParser(Parser.JSON)
                .when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
        System.out.println(gc.getLinkedIn());
        System.out.println(gc.getExpertise());
        System.out.println(gc.getInstructor());
        //System.out.println(gc.getCourses().getApi().get(1).getCourseTitle()) ;


        //to dynamically iterate
        List<Api> apiCourses=gc.getCourses().getApi();
        for(int i=0;i< apiCourses.size();i++){
            //  String courseTitle= gc.getCourses().getApi().get(i).getCourseTitle();
            if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
            {
                System.out.println(apiCourses.get(i).getPrice());
            }
        }


// to retrieve course titles from webAutomation array

        ArrayList<String> a= new ArrayList<String>();
        List<WebAutomation> webAutomationCourses= gc.getCourses().getWebAutomation();
        System.out.println(webAutomationCourses);
        for(int j=0;j<webAutomationCourses.size();j++){
            a.add(webAutomationCourses.get(j).getCourseTitle());



        }
        //to compare array and arraylist need to convert arrays to array list first
        List<String> expectedList= Arrays.asList(courseTitles);
        Assert.assertTrue(a.equals(expectedList));

 }
}
