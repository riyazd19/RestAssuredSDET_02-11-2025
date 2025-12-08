package com.api.test;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class LoginAPITest {


    @Test(description="verify if api is working")
    public void logintest(){
        RestAssured.baseURI="";
        RequestSpecification rs =RestAssured.given();

    }
}
