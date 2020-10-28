package com.chancetop.autotest.login;

import com.chancetop.services.GetSessionService;
import com.chancetop.services.SessionModel;
import com.chancetop.testbase.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * auther:kenychen
 *
 */
public class Login extends TestBase {
    public String weburl=TestBase.WEB_URL;

    public String reqpath="/user/login";
    public String reqbody="{\"email\":\"stress00001@chancetop.com\",\"password\":\"pwd11111\",\"type\":\"NORMAL\"}";

    GetSessionService getSessionService= new GetSessionService();

    @Test(description = "Mobile-api Login", priority = 0)
    public void testLogin() {

        SessionModel sessionModel=getSessionService.postSession();


        Map<String, String> headers = new HashMap<String,String>();
        headers.put("Content-Type","application/json;charset=UTF-8");
        headers.put("x-anonymous", "true");
        headers.put("x-client-id", TestBase.x_clientId);
        headers.put("x-session-token",sessionModel.Session_Token);
        headers.put("deviceid",sessionModel.device_id);

        Response response=  given().
                headers(headers).
                when().log().all().
                body(reqbody).
                put(weburl+reqpath).
                then().statusCode(200).
                extract().response();

    }

    @Test(description = "No registered users Login", priority = 0)
    public void testNoRegLogin() {

        SessionModel sessionModel=getSessionService.postSession();
        Map<String, String> headers = new HashMap<String,String>();
        headers.put("Content-Type","application/json;charset=UTF-8");
        headers.put("x-anonymous", "true");
        headers.put("x-client-id", TestBase.x_clientId);
        headers.put("x-session-token",sessionModel.Session_Token);
        headers.put("deviceid",sessionModel.device_id);

        String noRegEmailbody="{\"email\":\"9999900001@chancetop.com\",\"password\":\"pwd11111\",\"type\":\"NORMAL\"}";

        Response response=  given().
                headers(headers).
                when().log().all().
                body(noRegEmailbody).
                put(weburl+reqpath).
                then().statusCode(404).
                extract().response();

    }

    @Test(description = "There is a user but the password is wrong", priority = 1)
    public void testExistUserErrorPwdLogin() {

        SessionModel sessionModel=getSessionService.postSession();
        Map<String, String> headers = new HashMap<String,String>();
        headers.put("Content-Type","application/json;charset=UTF-8");
        headers.put("x-anonymous", "true");
        headers.put("x-client-id", TestBase.x_clientId);
        headers.put("x-session-token",sessionModel.Session_Token);
        headers.put("deviceid",sessionModel.device_id);

        String noRegEmailbody="{\"email\":\"stress00001@chancetop.com\",\"password\":\"pwd22222\",\"type\":\"NORMAL\"}";

        Response response=  given().
                headers(headers).
                when().log().all().
                body(noRegEmailbody).
                put(weburl+reqpath).
                then().statusCode(401).
                extract().response();
    }


    @Test(description = "There are users but the password is too long",priority = 1)

    public void testExistUserLongPwdLogin() {

        SessionModel sessionModel=getSessionService.postSession();
        Map<String, String> headers = new HashMap<String,String>();
        headers.put("Content-Type","application/json;charset=UTF-8");
        headers.put("x-anonymous", "true");
        headers.put("x-client-id", TestBase.x_clientId);
        headers.put("x-session-token",sessionModel.Session_Token);
        headers.put("deviceid",sessionModel.device_id);

        String noRegEmailbody="{\"email\":\"stress00001@chancetop.com\",\"password\":\"pwd22222111111111111111111111111111111111111111111111111\",\"type\":\"NORMAL\"}";

        Response response=  given().
                headers(headers).
                when().log().all().
                body(noRegEmailbody).
                put(weburl+reqpath).
                then().statusCode(500).
                extract().response();

        System.out.println("Headers====:\n"+response.getHeaders().toString());
    }



}
