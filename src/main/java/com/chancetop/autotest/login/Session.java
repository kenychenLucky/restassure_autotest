package com.chancetop.autotest.login;

import com.chancetop.testbase.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
public class Session extends TestBase {
    public String DEVICEID="";
    public String SESSION_TOKEN="";
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
    @Test
    public void testPutSession() {

        Map<String, String> headers = new HashMap<String,String>();
        headers.put("Content-Type","application/json;charset=UTF-8");
        headers.put("x-anonymous", "true");
        headers.put("x-client-id",TestBase.x_clientId);

        Response response=  given().
                headers(headers).
                when().log().all().
                body(reqbody).
                post(weburl+reqpath).
                then().
                extract().response();
        DEVICEID=response.path("device_id");
        SESSION_TOKEN=response.path("session_token");
        System.out.println(DEVICEID);
        System.out.println(SESSION_TOKEN);
    }

    public String getDEVICEID() { return DEVICEID; }
    public String getSESSIONTOKEN(){
        return SESSION_TOKEN;
    }


}
