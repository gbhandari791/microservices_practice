package com.auth.util;

public class GeneralUtil {
    public static String concat(String... str) {

        if(str == null) return null;

        StringBuffer sb = new StringBuffer();
        for(String s : str) {
            if(s != null) sb.append(s);
        }

        return sb.toString();
    }
}
