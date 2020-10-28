package com.chancetop.services;

import com.chancetop.models.user.UserRegisterModel;
import com.chancetop.testbase.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * auther:kenychen
 * date:2020/10/28
 */
public class LoginService {
    public String weburl= TestBase.WEB_URL;

    public String Login(String mail,String pwd) {
        GetSessionService getSessionService= new GetSessionService();

        String reqbody="{\"email\":\""+mail+"\",\"password\":\""+pwd+"\",\"type\":\"NORMAL\"}";
        String reqpath="/user/login";
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
        return response.path("user_id");

    }

    public UserRegisterModel LoginRetModel(String mail, String pwd) {
        GetSessionService getSessionService= new GetSessionService();

        String reqbody="{\"email\":\""+mail+"\",\"password\":\""+pwd+"\",\"type\":\"NORMAL\"}";
        String reqpath="/user/login";
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
        UserRegisterModel userRegisterModel=new UserRegisterModel();
        userRegisterModel.sessionToken=sessionModel.Session_Token;
        userRegisterModel.deviceId= sessionModel.device_id;
        userRegisterModel.user_id=response.path("user_id");
        return userRegisterModel;

    }
}
