package com.chancetop.testbase;

import java.util.HashMap;
import java.util.Map;

public class TestBase {
    public static String WEB_URL="https://mobile-api.foodtruck-staging.com";
    public static String x_clientId="f74f7d9c15cd4dbabe540e32abf0ec3a";
    public static String STRIPE_URL="https://api.stripe.com";

    public static Map setDefaultHeaders(String sessionToken,String deviceId){
        Map<String, String> headers = new HashMap<String,String>();
        headers.put("Content-Type","application/json;charset=UTF-8");
        headers.put("x-anonymous", "true");
        headers.put("User-Agent","PostmanRuntime/7.26.5");
        headers.put("x-client-id", TestBase.x_clientId);
        headers.put("x-session-token",sessionToken);
        headers.put("deviceid",deviceId);
        headers.put("Accept-Encoding","gzip, deflate, br");
        return headers;
    }

    public static Map setStripeHeaders(){
        Map<String, String> headers = new HashMap<String,String>();
        headers.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("X-Stripe-Client-User-Agent", "{\"os.name\":\"android\",\"os.version\":\"23\",\"bindings.version\":\"14.5.0\",\"lang\":\"Java\",\"publisher\":\"Stripe\",\"http.agent\":\"Dalvik\\/2.1.0 (Linux; U; Android 6.0; Android SDK built for x86_64 Build\\/MASTER)\"}");
        headers.put("Stripe-Version","2020-03-02");
        headers.put("User-Agent", "Stripe/v1 AndroidBindings/14.5.0");

        headers.put("Authorization","Bearer pk_test_51HPiztFEkkJdIyqPW89LLE6sekCtQUKZuk73c1xgC30nuLTYOeb2VXcoUSBE0N7U2P6ZaZ9ruiTDFSN3Cw9SEk1v00Bn3tjCS7");
        headers.put("Host","api.stripe.com");
        headers.put("Accept-Encoding","gzip");
        return headers;

    }



}
