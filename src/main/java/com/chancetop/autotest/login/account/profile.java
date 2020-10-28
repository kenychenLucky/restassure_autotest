package com.chancetop.autotest.login.account;

import com.chancetop.models.user.UserRegisterModel;
import com.chancetop.services.GetSessionService;
import com.chancetop.services.LoginService;
import com.chancetop.services.SessionModel;
import com.chancetop.testbase.TestBase;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class profile extends TestBase {
    public String weburl=TestBase.WEB_URL;

    public String reqpath="/account/profile";
    public String reqbody="";
    GetSessionService getSessionService= new GetSessionService();
    @Test(description = "Mobile-api no login profile Test", priority = 0)
    public void testNoLoginGetProfile() {
        SessionModel sessionModel=getSessionService.postSession();
        Map<String, String> headers = TestBase.setDefaultHeaders(sessionModel.Session_Token,sessionModel.device_id);
        Response response=  given().
                headers(headers).
                when().log().all().
                body(reqbody).
                get(weburl+reqpath).
                then().
                extract().
                response();
        responseJsonNoLogin(response);
        System.out.println(response.toString());

    }

    public static void  responseJsonNoLogin(Response res){
        String login_required= res.jsonPath().get("error_code");
        String error_message= res.jsonPath().get("error_message");
        Assert.assertEquals(login_required,"LOGIN_REQUIRED");
        Assert.assertEquals(error_message,"failed to authenticate user, please login first");
    }
    @Test(description = "Mobile-api Login profile Test", priority = 0)
    public void testLoginGetProfile() {

        LoginService loginService= new LoginService();
        UserRegisterModel userRegisterModel=loginService.LoginRetModel("stress00001@chancetop.com","pwd11111");
        Map<String, String> headers = TestBase.setDefaultHeaders(userRegisterModel.sessionToken,userRegisterModel.deviceId);
        Response response=  given().
                headers(headers).
                when().log().all().
                body(reqbody).
                get(weburl+reqpath).
                then().
                extract().
                response()
                ;

        String email= response.jsonPath().get("email");
        String first_name= response.jsonPath().get("first_name");
        String last_name= response.jsonPath().get("last_name");
        String birthday_month= response.jsonPath().get("birthday_month");
        String phone= response.jsonPath().get("phone");
        Assert.assertEquals(email,"stress00001@chancetop.com");
        Assert.assertEquals(first_name,"Stress");
        Assert.assertEquals(last_name,"Test");
        Assert.assertEquals(birthday_month,null);
        Assert.assertEquals(phone,"7457181001");



    }




}
