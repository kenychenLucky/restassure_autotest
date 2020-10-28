package com.chancetop.autotest.stripe;

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

/**
 * auther:kenychen
 */
public class Stripe {

    public String weburl= TestBase.STRIPE_URL;

    @Test(description = "Mobile-api stripe Test", priority = 0)
    public void testApiStripe() {
        String reqbody="billing_details%5Baddress%5D%5Bpostal_code%5D=14432&type=card&muid=c8e28f22-eacc-4492-928a-10b182af4f3c&card%5Bexp_year%5D=2034&card%5Bnumber%5D=4242424242424242&card%5Bcvc%5D=234&card%5Bexp_month%5D=12";
        String reqpath="/v1/payment_methods";
        Map<String, String> headers = TestBase.setStripeHeaders();

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
