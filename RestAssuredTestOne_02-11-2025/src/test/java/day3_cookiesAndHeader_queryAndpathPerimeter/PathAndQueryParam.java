package day3_cookiesAndHeader_queryAndpathPerimeter;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PathAndQueryParam {
    String apiKey = "reqres_c0fbd0beefd84903b3a43e78e249374d";
    //https://reqres.in/api/users?page=2&id=5
    //testing a get request
    @Test
    void testQueryAndPathParam() {
        given()
                .header("x-api-key", apiKey)
                .pathParam("mypath", "users")
                .queryParam("page", 2)
                .queryParam("id", 2)

                .when().get("https://reqres.in/api/{mypath}")
                .then().statusCode(200).log().all();
    }

    //cookies and headers


}
