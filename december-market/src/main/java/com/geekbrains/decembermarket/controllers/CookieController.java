package com.geekbrains.decembermarket.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/cookie")
public class CookieController {

    @GetMapping("/read")
    @ResponseBody
    public void readCookie(@CookieValue(value = "marketProduct", required = false) Cookie cookieName,
                           HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("CookieController readCookie() is called");
        String cookieValue = "";
        if (cookieName != null) {
            cookieValue  = "[Object: " + cookieName + "; Name: " + cookieName.getName() + "; Value: " + cookieName.getValue() + "]";
            System.out.println(cookieValue);
        } else {
            response.sendRedirect("/market/cookie/write");
        }
    }

    @GetMapping("/write")
    @ResponseBody
    public void writeCookie(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("CookieController writeCookie() is called");
        Cookie cookie = new Cookie("marketProduct", request.getRequestURL().toString());
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
        System.out.println("Object: " + cookie + "; Name: " + cookie.getName() + "; Value: " + cookie.getValue());
    }


//    @GetMapping("/readAll")
//    @ResponseBody
//    public void readAllCookies(HttpServletRequest request) {
//        Cookie[] cookies = request.getCookies();
//        System.out.println("All Cookies in your browsers");
//        String cookiesStr = "";
//        for(Cookie cookie : cookies){
//            System.out.println(cookie.getName() + " : " + cookie.getValue());
//            cookiesStr += cookie.getName() + " : " + cookie.getValue() + " : " + cookie + "<br/>";
//        }
//    }
}
