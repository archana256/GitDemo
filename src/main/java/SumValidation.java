import files.payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {

    @Test
    public void SumOfPurchase(){
        JsonPath js =new JsonPath(payload.coursePrice());
        int sum=0;

        int count=js.getInt("courses.size()");
        int purchaseAmount = js.get("dashboard.purchaseAmount");
        for(int i=0; i<count; i++){
            int price= js.getInt("courses["+i+"].price");
            int copies= js.getInt("courses["+i+"].copies");
                    int amount=price*copies;
            sum = sum+ amount;
        }

        System.out.println(sum);
        Assert.assertEquals(sum,purchaseAmount);
    }
}
