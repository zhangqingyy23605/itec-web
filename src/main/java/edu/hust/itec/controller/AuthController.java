package edu.hust.itec.controller;

import edu.hust.itec.model.User;
import edu.hust.itec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * Created by xsh on 2015/4/30.
 */

@Controller
@RequestMapping("/auth")
//@SessionAttributes({"user", "referer"})
public class AuthController {
    @Autowired
    private UserService userService;

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
    public String loginView(ModelMap model) {
//        model.addAttribute("categoryList", authService.getCategoryLeaves());

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
    public String register(User user, HttpSession session, HttpServletRequest request) {
        try {
            userService.saveOrUpdate(user);
        } catch (Exception e) {

        }
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