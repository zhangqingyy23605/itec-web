package edu.hust.itec.controller;

import edu.hust.itec.model.Student;
import edu.hust.itec.model.Teacher;
import edu.hust.itec.model.User;
import edu.hust.itec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sun.plugin.javascript.ReflectUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by xsh on 2015/4/30.
 */

@Controller
@RequestMapping("/auth")
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
    @RequestMapping(value= "back")
    public String back(HttpSession session) {
        String referer = (String)session.getAttribute("refererAuth");
        session.removeAttribute("refererAuth");
        if(referer != null) {
            return "redirect:" + referer;
        } else {
            return "redirect:/";
        }
    }

    //登陆
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginView() {
        return "auth/login";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, BindingResult result, HttpSession session) {
        //TODO 用户名密码错误的回显
        User resultUser = userService.get(user);
        if (resultUser == null) {
            result.rejectValue("username", "用户名或密码错误");
        }
        if(result.hasErrors()) {
            return "redirect:/auth/login";
        }
        session.setAttribute("auth", resultUser);
        return "redirect:/auth/back";
    }

    //注销
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("auth");
        return "redirect:/auth/back";
    }

    @RequestMapping(value = "/validUsername", method= RequestMethod.GET)
    public @ResponseBody String exists(User user) {
        if (userService.get(user) == null) {
            return "true";
        } else {
            return "false";
        }
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid User user, BindingResult result, HttpSession session) {
        //虽然前端已经验证过，但是要防止恶意POST请求，形成数据库中用户名相同的用户
        User username = new User();
        username.setUsername(user.getUsername());
        if (userService.get(username) != null) {
            result.rejectValue("username", "用户名" + username + "已经被使用");
        }
        String type = user.getType();
        if (type == "TEACHER") {
            userService.save(new Teacher(user));
        } else if (type == "STUDENT") {
            userService.save(new Student(user));
        } else {
            result.rejectValue("type", "用户类型为教师或者学生");
        }
        if(result.hasErrors()) {
            return "redirect:/auth/login";
        }

        session.setAttribute("auth", user);
        return "redirect:/auth/back";
    }
    //TODO 邮箱验证；找回密码
}