package com.chancetop.services;

import com.chancetop.testbase.TestBase;
import com.chancetop.utils.TestData;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SmsVerificationCodeService {

    public String weburl= TestBase.WEB_URL;

    public String reqpath="/user/sms-verification-code";

    public String reqbody="";

    GetSessionService getSessionService= new GetSessionService();


    public String  smsVerificationCode(String  phone) {

        SessionModel sessionModel=getSessionService.postSession();

        reqbody="{\n" +
                "    \"phone\": \""+phone+"\",\n" +
                "    \"email\": null\n" +
                "}";


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
                then().statusCode(204).
                extract().response();
        return phone;


    }


}
