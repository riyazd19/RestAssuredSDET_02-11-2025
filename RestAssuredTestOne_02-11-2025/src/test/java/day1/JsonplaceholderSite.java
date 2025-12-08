package day1;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class JsonplaceholderSite {
    int postId;

    @Test(priority = 1)
    void listUser(){
        given().when().get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(200);
                //.log().all();
    }
    @Test(priority =2)
    void createUser(){
        HashMap data= new HashMap();
        data.put("title","foo");
        data.put("body","bar");
        data.put("userId","1");
       postId= given().contentType("application/json").body(data).
                when().post("https://jsonplaceholder.typicode.com/posts")
                .then().statusCode(201).extract().jsonPath().getInt("id");
        System.out.println(postId);
    }
    @Test(priority = 3)
    void getCreatedUserDetails(){
        given().when().get("https://jsonplaceholder.typicode.com/posts/"+postId)
                .then().statusCode(200).log().all();

    }

}
