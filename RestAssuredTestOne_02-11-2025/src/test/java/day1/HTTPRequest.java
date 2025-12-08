package day1;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HTTPRequest {
    int id;

    String apiKey = "reqres_c0fbd0beefd84903b3a43e78e249374d";

    @Test(priority = 1)
    void listUsers() {

        given()
                .header("x-api-key", apiKey)
                .accept("application/json")
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", equalTo(2))
        //.log().all()
        ;

    }

    @Test(priority = 2)
    void create_User() {

        HashMap<String, String> data = new HashMap<>();
        data.put("name", "Pawan");
        data.put("job", "QA Engineer");

        id = given()
                .header("x-api-key", apiKey)
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");
    }

    @Test(priority = 3,dependsOnMethods = {"create_User"})
    void update_User() {

        HashMap<String, String> data = new HashMap<>();
        data.put("name", "Pawan");
        data.put("job", "QA Engineer updated");

        given()
                .header("x-api-key", apiKey)
                .contentType("application/json")
                .body(data)
                .when()
                .put("https://reqres.in/api/users/" + id)
                .then().statusCode(200).log().all();

    }
}
