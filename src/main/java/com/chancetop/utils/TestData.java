package com.chancetop.utils;


import java.util.Random;

public class TestData {

    public static String regUserPhone() {

        String phone = Strings.format("{}{}{}{}", Randoms.nextInt(2, 9), Randoms.nextInt(10, 99), Randoms.nextInt(2, 9), Randoms.nextInt(100000, 999999));
        return phone;
    }


    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

}
