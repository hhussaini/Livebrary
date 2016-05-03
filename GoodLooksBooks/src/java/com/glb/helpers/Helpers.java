package com.glb.helpers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    
    public static void goToSignIn(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        String url = "/signIn.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response); 
    }
    
    public static double round(double num){
        return (double)Math.round(num * 100) / 100;
    }
    
    public static void outputToHtml(HttpServletResponse response, String text) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<body>");
            out.println("<p>" + text + "</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    public static String getTagFromXmlStr(String xmlStr, String tag) {
        String result = xmlStr.substring(xmlStr.indexOf("<" + tag + ">") + tag.length() + 2, xmlStr.indexOf("</" + tag + ">"));
        return result;
    }
}
