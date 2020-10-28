package com.chancetop.utils;

import org.slf4j.helpers.MessageFormatter;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author kenychen
 */
public final class Strings {
    public static byte[] bytes(String text) {
        return text.getBytes(UTF_8);
    }

    public static String format(String pattern, Object... params) {
        return MessageFormatter.arrayFormat(pattern, params).getMessage();
    }




    public static boolean startsWith(String text, char prefix) {
        if (text.isEmpty()) return false;
        return text.charAt(0) == prefix;
    }


}
