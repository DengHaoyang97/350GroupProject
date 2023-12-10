package com.myproject.onlinelearningsystem.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/api/users")
public class CookieController {

    @GetMapping("/setCookie")
    public String setCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("myCookie", "cookieValue");
        cookie.setMaxAge(7 * 24 * 60 * 60); // 设置 Cookie 有效期为 7 天
        cookie.setPath("/"); // 设置路径
        response.addCookie(cookie);
        return "Cookie is set";
    }

    @GetMapping("/getCookie")
    public String getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("myCookie".equals(cookie.getName())) {
                    return "Cookie Value: " + cookie.getValue();
                }
            }
        }
        return "No cookie found";
    }

    @GetMapping("/deleteCookie")
    public String deleteCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("myCookie".equals(cookie.getName())) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    return "Cookie deleted";
                }
            }
        }
        return "No cookie found to delete";
    }

}
