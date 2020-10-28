package com.chancetop.services;

import com.chancetop.testbase.TestBase;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class GetSessionService {

    public String weburl=TestBase.WEB_URL;

    public String reqpath="/v2/session";
    public String reqbody="{\n" +
            "        \"device\": { \n" +
            "        \t \"device_id\": null,\n" +
            "            \"id\": null\n" +
            "        \t},\n" +
            "    \n" +
            "    \"version\": \"1.21.0\",\n" +
            "    \"session_token\": null\n" +
            "}";

    public SessionModel postSession() {

        SessionModel sessionModel = new SessionModel();

        Map<String, String> headers = new HashMap<String,String>();
        headers.put("Content-Type","application/json;charset=UTF-8");
        headers.put("x-anonymous", "true");
        headers.put("x-client-id", TestBase.x_clientId);

        Response response=  given().
                headers(headers).
                when().log().all().
                body(reqbody).
                post(weburl+reqpath).
                then().
                extract().response();
        System.out.println(response.asString());

        try {
            sessionModel.device_id = response.path("device_id");
            sessionModel.Session_Token = response.path("session_token");
        }
        catch (Exception ex){
            System.out.println("Active Session fold!");
            ex.printStackTrace();
        }

        return sessionModel;


    }
}
