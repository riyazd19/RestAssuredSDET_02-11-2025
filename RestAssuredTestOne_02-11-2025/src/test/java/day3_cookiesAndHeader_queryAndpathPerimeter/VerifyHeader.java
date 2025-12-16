package day3_cookiesAndHeader_queryAndpathPerimeter;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class VerifyHeader {

    @Test(priority = 0)
    void testHeader(){
        given().when().get("https://www.google.com")
                .then().
                header("Content-Type","text/html; charset=ISO-8859-1")
                .and()
                .header("Content-Encoding","gzip")
                .and()
                .header("Server","gws")
        ;
    }
    @Test(priority = 4)
    void getheaders(){

        Response res= given().when().get("https://www.google.com");
        //get single header info
      String content_type=  res.getHeader("Content-Type");
        System.out.println(content_type);
    }
    @Test(priority = 0)
    void getAllHeaders(){
        int i=1;
        Response res= given().when().get("https://www.google.com");
         Headers headers= res.getHeaders();
         for(Header x: headers){
             System.out.println(x.getName()+" "+ i +"  "+x.getName());
             i++;
         }

    }
}
