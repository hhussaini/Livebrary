package com.glb.helpers;

/**
 * @author mobile-mann
 */

public class Helpers {
    public static void print(String str) {
        System.out.print(str);
    }
    public static void println(String str) {
        System.out.println(str);
    }
    public static void print(int num) {
        System.out.print(num);
    }
    public static void println(int num) {
        System.out.println(num);
    }
    public static String appendParameter(String url, String parameter, String value, boolean firstParam) {
        if (firstParam) {
            return url + "?" + parameter + "=" + value;
        } else {
            return url + "&" + parameter + "=" + value;
        }
    }
}
