package files;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.File;

import static io.restassured.RestAssured.*;

public class JiraTest {

    public static void main(String args[]){
        SessionFilter session =new SessionFilter();

        //To get session cookie
        RestAssured.baseURI="http://localhost:8080/";
      String sessionResponse=  given().relaxedHTTPSValidation().log().all().header("Content-Type","application/json")
                .body("{ \"username\": \"archana\", \"password\": \"Password1@\" }").filter(session)
                .when().post("/rest/auth/1/session")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

      // add comment
        String expectedComment ="Hi,How are you?";
       String addComment= given().log().all().pathParam("Key","10100").header("Content-Type","application/json")
                .body("{\n" +
                        "    \"body\": \""+expectedComment+"\",\n" +
                        "    \"visibility\": {\n" +
                        "        \"type\": \"role\",\n" +
                        "        \"value\": \"Administrators\"\n" +
                        "    }\n" +
                        "}").filter(session)
                .when().post("rest/api/2/issue/{Key}/comment")
                .then().log().all().assertThat().statusCode(201).extract().response().asString();

        JsonPath js=new JsonPath(addComment);
        String commentId= js.getString("id");

        //add attachment

        given().log().all().header("X-Atlassian-Token","no-check").
                pathParam("Key","10100").filter(session)
                .multiPart("file",new File("C:\\Users\\archana.b\\IdeaProjects\\MavenTest\\src\\main\\java\\jira.txt"))
                .when().post("rest/api/2/issue/{Key}/attachments")
                .then().log().all().assertThat().statusCode(200);

        //get issue details

      String issueResponse =  given().log().all().filter(session).pathParam("Key","10100")
                .queryParam("field","comments")
                .when().get("rest/api/2/issue/{Key}")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

      JsonPath js1=new JsonPath(issueResponse);
      int commentCount = js1.getInt("fields.comment.comments.size()");
        System.out.println(commentCount);

      for(int i=0;i<commentCount;i++){
          String CommentIdIssue=js1.get("fields.comment.comments["+i+"].id").toString();
          System.out.println(CommentIdIssue);
          if(CommentIdIssue.equalsIgnoreCase(commentId)){
              String actualComment=js1.get("fields.comments.comment["+i+"].body").toString();
              System.out.println(actualComment);
              Assert.assertEquals(actualComment,expectedComment);
          }
      }





    }
}
