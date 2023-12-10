package com.myproject.onlinelearningsystem.controller;
import com.myproject.onlinelearningsystem.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping("/" )
    public String RedirectLogin() {
        return "redirect:/login";
    }

    @GetMapping("/login" )
    public String login(HttpSession session, Model model) {
        String cookieid = (String) session.getAttribute("Cookie");
        String user_type = (String) session.getAttribute("type");

        if(cookieid != null){
            return "redirect:/Redirect";
        }

        return "login";
    }

    @PostMapping("/login")
        public String processLogin(String id, String password, String user_type, HttpServletResponse response, HttpSession session) {
            System.out.println("Login as " + user_type);
            String cookieid=null;
            switch (user_type) {
                case ("Student"):
                    cookieid = loginService.login_student(id, password);
                    break;
                case ("Staff"):
                    cookieid = loginService.login_staff(id, password);
                    break;
                case ("Admin"):
                    cookieid = loginService.login_admin(id, password);
                    break;
            }
            System.out.println("Logined as id = " + cookieid);
            if(cookieid == null){
                return "redirect:/login";
            }
            else {
                Cookie cookie = new Cookie("OLS", cookieid);
                session.setAttribute("Cookie", cookieid);
                session.setAttribute("type", user_type);
                return "redirect:/index";
            }

    }
        @GetMapping("/logout")
        public String logout(HttpSession session) {
            session.invalidate();

            return "redirect:/login";

    }

    @GetMapping("/Redirect")
    public String Redirect() {
        return "Redirect";
    }
        }



