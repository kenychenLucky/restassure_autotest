package com.chancetop.autotest.reguser;

import com.chancetop.services.GetSessionService;
import com.chancetop.services.SessionModel;
import com.chancetop.services.SmsVerificationCodeService;
import com.chancetop.testbase.TestBase;
import com.chancetop.utils.TestData;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RegUser {

    //sms-verification-code


    public String weburl= TestBase.WEB_URL;

    public String reqpath="/user/sms-verification-code";

    public String reqbody="";

    GetSessionService getSessionService= new GetSessionService();

    @Test(description = "Mobile-api sms-verification-code Test", priority = 0)
    public void testsmsVerificationCode() {

        SessionModel sessionModel=getSessionService.postSession();
        String phone= TestData.regUserPhone();
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
                then().
                extract().response();
    }


    @Test(description = "Mobile-api Reg User Test", priority = 0)
    public void testRegUser() {
        reqpath="/v2/user/register";
        SessionModel sessionModel=getSessionService.postSession();
        String phone= TestData.regUserPhone();

        SmsVerificationCodeService scs= new SmsVerificationCodeService();
        scs.smsVerificationCode(phone);

        String tmp= TestData.getRandomString(4).toLowerCase();

        reqbody="{\n" +
                "    \"email\": \""+phone+tmp+"@chancetop.com\",\n" +
                "    \"full_name\": \"autotest"+tmp+"\",\n" +
                "    \"first_name\": \"Stress\",\n" +
                "    \"last_name\": \"Test\",\n" +
                "    \"password\": \"pwd11111\",\n" +
                "    \"type\": \"NORMAL\",\n" +
                "    \"phone\": \""+phone+"\",\n" +
                "    \"code\": \"123456\"\n" +
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
                then().statusCode(200).
                extract().response();
        System.out.println(response.asString());

    }



}
