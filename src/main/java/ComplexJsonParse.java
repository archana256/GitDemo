import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

    public static void main(String args[]) {
        JsonPath js = new JsonPath(payload.coursePrice());

        //No of courses returned by api
        int CourseCount = js.getInt("courses.size()");
        System.out.println(CourseCount);
        //print purchase amount

        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);

        //to get first course title
        String firstTitlecourse = js.get("courses[0].title");
        System.out.println(firstTitlecourse);

        //print all courses & their prices
        for (int i = 0; i < CourseCount; i++) {
            String allCourseTitles = js.get("courses[" + i + "].title");
            System.out.println(js.get("courses[" + i + "].price").toString()); //if dont write toString
            //then not possible to use above line with sysout since it expects string argument hence we are converting o/p to string

            System.out.println(allCourseTitles);
        }

        //print no of copies sold by RPA Course
        System.out.println("Copies sold by RPA Course :");
        for (int i = 0; i < CourseCount; i++) {
            String allCourseTitles = js.get("courses[" + i + "].title");
            if (allCourseTitles.equalsIgnoreCase("RPA")) {
                int CopiesCount = js.get("courses[" + i + "].copies");
                System.out.println(CopiesCount);
                break;
            }
        }
    }


}
