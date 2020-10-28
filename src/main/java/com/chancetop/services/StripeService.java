package com.chancetop.services;

import com.chancetop.testbase.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class StripeService {
    public String weburl= TestBase.STRIPE_URL;

    public String reqpath="/v1/payment_methods";

    public String reqbody="";

    public String getRequestApiStripeId() {

        reqbody="billing_details%5Baddress%5D%5Bpostal_code%5D=14432&type=card&muid=c8e28f22-eacc-4492-928a-10b182af4f3c&card%5Bexp_year%5D=2034&card%5Bnumber%5D=4242424242424242&card%5Bcvc%5D=234&card%5Bexp_month%5D=12";

        Map<String, String> headers = new HashMap<String,String>();
        headers.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("X-Stripe-Client-User-Agent", "{\"os.name\":\"android\",\"os.version\":\"23\",\"bindings.version\":\"14.5.0\",\"lang\":\"Java\",\"publisher\":\"Stripe\",\"http.agent\":\"Dalvik\\/2.1.0 (Linux; U; Android 6.0; Android SDK built for x86_64 Build\\/MASTER)\"}");
        headers.put("Stripe-Version","2020-03-02");
        headers.put("User-Agent", "Stripe/v1 AndroidBindings/14.5.0");

        headers.put("Authorization","Bearer pk_test_51HPiztFEkkJdIyqPW89LLE6sekCtQUKZuk73c1xgC30nuLTYOeb2VXcoUSBE0N7U2P6ZaZ9ruiTDFSN3Cw9SEk1v00Bn3tjCS7");
        headers.put("Host","api.stripe.com");
        headers.put("Accept-Encoding","gzip");

        Response response=  given().
                headers(headers).
                when().log().all().
                body(reqbody).
                post(weburl+reqpath).
                then().statusCode(200).
                extract().response();
        return response.path("id");

    }
}
