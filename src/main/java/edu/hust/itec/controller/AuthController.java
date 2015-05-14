package edu.hust.itec.controller;

import edu.hust.itec.model.News;
import edu.hust.itec.model.User;
import edu.hust.itec.service.AuthService;
import edu.hust.itec.service.NewsService;
import edu.hust.itec.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by xsh on 2015/4/30.
 */

@Controller
@RequestMapping("/auth")
//@SessionAttributes({"user", "referer"})
public class AuthController {
//    @Autowired
    private AuthService authService;

    @RequestMapping
    public String authRoute(HttpServletRequest request, HttpSession session) {
        //save referer
        String referer = request.getHeader("Referer");
        if(referer != null) {
            session.setAttribute("refererAuth", referer);
        }
        //route according state
        User user = (User) session.getAttribute("auth");
        if (user == null) {
            return "redirect:/auth/login";//进入登陆界面
        } else {
            return "redirect:/auth/logout";//进入管理界面
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginView() {
        return "auth/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpSession session) {
        User user = new User();
        user.setUsername("root");
        session.setAttribute("auth", user);

        String referer = (String)session.getAttribute("refererAuth");
        session.removeAttribute("refererAuth");
        if(referer != null) {
            return "redirect:" + referer;
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("auth");

        String referer = (String)session.getAttribute("refererAuth");
        session.removeAttribute("refererAuth");
        if(referer != null) {
            return "redirect:" + referer;
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(User user, HttpSession session) {


//        authService.save(user);

        user.setUsername("root");
        session.setAttribute("auth", user);

        String referer = (String)session.getAttribute("refererAuth");
        session.removeAttribute("refererAuth");
        if(referer != null) {
            return "redirect:" + referer;
        } else {
            return "redirect:/";
        }
    }

}