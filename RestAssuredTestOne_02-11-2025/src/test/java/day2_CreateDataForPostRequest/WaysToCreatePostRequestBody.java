package day2_CreateDataForPostRequest;

import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class WaysToCreatePostRequestBody {
    //  URI  http://localhost:3000/students
    //using HashMap
    String id;


    //@Test(priority = 1)
    void testPostUsingHashMap()
    {
        HashMap data= new HashMap<>();
        /*
        "firstName": "Arjun",
    "lastName": "Chopra",
    "fullName": "Arjun Chopra",
    "gender": "Female",
    "dateOfBirth": "2005-03-05",
    "age": 20,
    "email": "arjun.chopra1@example.com",
    "phone": "9727384839",
    "address": {
      "city": "Delhi",
      "state": "India",
      "zipCode": 234987

         */
        data.put("firstName","ArjunSecond");
        data.put("fullName", "Arjun Chopra Second");
        //for address as there are multiple values for a key we need to keep them in Array
        //String[] residence ={"srinangar","Baramulla", "etc"};
        //data.put("address",residence);
      id=  given().contentType("application/json").body(data)
                .when().post("http://localhost:3000/students")
                .then().statusCode(201).body("firstName",equalTo("ArjunSecond")).extract().jsonPath().getString("id")
                ;
    }
  //  @Test(priority = 2,dependsOnMethods = {"testPostUsingHashMap"})
        void deleteCreatedUser(){

        given().when().delete("http://localhost:3000/students/"+id).then().statusCode(200);
        }
//Using org.json(in pom)
//@Test(priority = 3)
void testPostUsingOrgJson()
/*
 for Org.json while passing the data we need to change the json data to string (body(data.toString())
 */

{
    JSONObject data= new JSONObject();
    data.put("firstName","ArjunSecond");
    data.put("fullName", "Arjun Chopra Second");
    id=  given().contentType("application/json").body(data.toString())
            .when().post("http://localhost:3000/students")
            .then().statusCode(201).body("firstName",equalTo("ArjunSecond")).extract().jsonPath().getString("id")
    ;
}
//@Test(priority = 4,dependsOnMethods = {"testPostUsingOrgJson"})
    void deleteCreatedUserByOrgJSon(){
        given().when().delete("http://localhost:3000/students/"+id).then().statusCode(200);
    }

//Using pojo (plain old java object)
   // @Test(priority =5 )
    void createUserUsingPOJO(){
        POJO_ClassForPostReq data= new POJO_ClassForPostReq();
        data.setFirstName("ArjunSecond");
        data.setFullName("Arjun Chopra Second");

        id=given().contentType("application/json").body(data)
                .when().post("http://localhost:3000/students")
                .then().statusCode(201).body("firstName",equalTo("ArjunSecond")).extract().jsonPath().getString("id");
    }
    //@Test(priority = 6, dependsOnMethods = {"createUserUsingPOJO"})
    void deleteCreatedUserByPOJO(){
    given().when().delete("http://localhost:3000/students/"+id).then().statusCode(200);
    }

//using External json
    @Test(priority = 7)
    void createUserByExternalJson() throws FileNotFoundException {
    File file= new File(".\\body.json");
        FileReader fr= new FileReader(file);
        JSONTokener jt= new JSONTokener(fr);
        JSONObject jo = new JSONObject(jt);
        id=given().contentType("application/json").body(jo.toString())
                .when().post("http://localhost:3000/students").then()
                .statusCode(201).body("firstName",equalTo("Arjun Updated second")).extract()
                .jsonPath().getString("id");
    }
    @Test(priority = 8,dependsOnMethods = {"createUserByExternalJson"})
    void deleteCreatedUserByExtractedJson(){
        given().when().delete("http://localhost:3000/students/"+id).then().statusCode(200);
    }

}
