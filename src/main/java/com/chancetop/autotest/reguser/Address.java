package com.chancetop.autotest.reguser;

import com.chancetop.services.GetSessionService;
import com.chancetop.services.SessionModel;
import com.chancetop.testbase.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Address {

    public String weburl= TestBase.WEB_URL;

    public String reqpath="/v3/address";

    public String reqbody="{\n" +
            "\"ship_to\":null,\n" +
            "\"latitude\":\"40.6641628\",\n" +
            "\"longitude\":\"-74.3534779\",\n" +
            "\"address_name\":\"555 Birch Avenue\",\n" +
            "\"address_short_name\":\"555 Birch Ave\",\n" +
            "\"unit_number_or_company\":null,\n" +
            "\"state\":\"NJ\",\n" +
            "\"city\":\"Westfield\",\n" +
            "\"county\":\"Union County\",\n" +
            "\"zip_code\":\"07090\",\n" +
            "\"nickname\":null,\n" +
            "\"delivery_instructions\":null,\n" +
            "\"delivery_option\":\"MEET_AT_DOOR\"\n" +
            "}\n";

    GetSessionService getSessionService= new GetSessionService();

    @Test(description = "Mobile-api Address Test", priority = 0)
    public void testAddress() {

        SessionModel sessionModel=getSessionService.postSession();


        Map<String, String> headers = new HashMap<String,String>();
        headers.put("Content-Type","application/json;charset=UTF-8");
        headers.put("x-anonymous", "true");
        headers.put("User-Agent","PostmanRuntime/7.26.5");
        headers.put("x-client-id", TestBase.x_clientId);
        headers.put("x-session-token",sessionModel.Session_Token);
        headers.put("deviceid",sessionModel.device_id);
        headers.put("Accept-Encoding","gzip, deflate, br");

        Response response=  given().
                headers(headers).
                when().log().all().
                body(reqbody).
                post(weburl+reqpath).
                then().statusCode(200).
                extract().response();
        System.out.println(response.asString());

    }


}
