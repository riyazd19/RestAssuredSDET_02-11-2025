package day3_cookiesAndHeader_queryAndpathPerimeter;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class VerifyCookies {
    Response cookie;

    @Test(priority = 1)
    void testCookies() {
        given().when().get("https://www.google.com/")
                .then()
        ;
    }

    @Test(priority = 2)
    void verifyCookie() {
        cookie = given().when().get("https://www.google.com");
        String cookie_Value = cookie.getCookie("AEC");
        System.out.println(cookie_Value);
    }

    @Test(priority = 3)
    void getAllCookiesInfo() {
        Map<String, String> cookies_value = cookie.getCookies();
        System.out.println(cookies_value.keySet() + " " + cookies_value.values());
    }

}
