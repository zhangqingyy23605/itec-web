package edu.hust.itec.controller;

import edu.hust.itec.model.News;
import edu.hust.itec.model.User;
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
@SessionAttributes("user")
public class AuthController {
//    @Autowired
//    private AuthService AuthService;

    @RequestMapping
    public String auth(ModelMap model, HttpServletRequest request, HttpSession session, SessionStatus status) {
        User user =  (User)session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setUsername("root");
            model.addAttribute("user", user);
        } else {
            status.setComplete();
        }
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

//    @RequestMapping("/signin")
//    public String signin(@ModelAttribute User user) {
//        System.out.println("登陆");
//        return "redirect:/";
//    }
//
//    @RequestMapping("/signout")
//    public String signout(@ModelAttribute User user) {
//        System.out.println("登陆");
//        return "redirect:/";
//    }

}