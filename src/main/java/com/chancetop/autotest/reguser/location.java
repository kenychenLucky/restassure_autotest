package com.chancetop.autotest.reguser;

import com.chancetop.services.GetSessionService;
import com.chancetop.services.SessionModel;
import com.chancetop.testbase.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.head;

public class location  extends TestBase {
        public String weburl= TestBase.WEB_URL;

        public String reqpath="/v2/location";
        public String reqbody="{\n" +
                "\"latitude\":\"40.6641628\",\n" +
                "\"longitude\":\"-74.3534779\",\n" +
                "\"state\": \"NJ\",\n" +
                "\"city\": \"Westfield\",\n" +
                "\"zip_code\":\"07090\",\n" +
                "\"address_id\": null,\n" +
                "\"address_name\":\"555 Birch Avenue\",\n" +
                "\"address_short_name\":\"555 Birch Ave\",\n" +
                "\"county\":\"Union County\"\n" +
                "}\n" +
                "\n";

        GetSessionService getSessionService= new GetSessionService();

        @Test(description = "Mobile-api Location Test", priority = 0)
        public void testLocation() {
            SessionModel sessionModel=getSessionService.postSession();
            Map<String, String> headers = TestBase.setDefaultHeaders(sessionModel.Session_Token, sessionModel.device_id);

            Response response=  given().
                    headers(headers).
                    when().log().all().
                    body(reqbody).
                    put(weburl+reqpath).
                    then().statusCode(204).
                    extract().response();
            System.out.println(x_clientId);
            System.out.println(response.asString());

        }




}
