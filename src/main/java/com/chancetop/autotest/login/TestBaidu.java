package com.chancetop.autotest.login;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestBaidu {
    @Test
    public void testSearch(){
        given().log().all().get("http://www.baidu.com").then().log().all().statusCode(200);
    }
}
