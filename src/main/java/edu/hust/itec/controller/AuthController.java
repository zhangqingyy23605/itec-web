package edu.hust.itec.controller;

import edu.hust.itec.model.*;
import edu.hust.itec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    //登陆
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginView(ModelMap model) {
        if(!model.containsAttribute("user")) {//首次添加
            model.addAttribute("user", new User());
        }
        model.addAttribute("categoryList", this.userService.getCategoryLeaves());
        return "auth/login";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, BindingResult result, HttpSession session, RedirectAttributes redirectAttributes) {
        //TODO 用户名密码错误的回显
        User resultUser = userService.getByExample(user);
        if (resultUser == null) {
            result.rejectValue("username", "user not found", "用户名或密码错误");
        }
        if(result.hasErrors()) {
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "user", result);
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:/auth/login";
        }
        session.setAttribute("auth", resultUser);
        return "redirect:/admin";
    }

    //注销
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("auth");
        return "redirect:/";
    }

    //注册
    @RequestMapping(value = "/validUsername", method= RequestMethod.GET)
    public @ResponseBody String exists(User user) {
        if (userService.getByExample(user) == null) {
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
        if (userService.getByExample(username) != null) {
            result.rejectValue("username", "用户名" + username + "已经被使用");
        }
        //设置默认权限
        user.setPrivilege(User.Privilege.SELF);
        Integer userId;
        Category completeCategory = this.userService.getCateogyrByName(user.getCategory().getName());
        user.setCategory(completeCategory);
        Category upperCategory = completeCategory.getParent();
        if (upperCategory.getName().equalsIgnoreCase("教师") ) {
            userId = userService.save(new Teacher(user));
        } else if (upperCategory.getName().equalsIgnoreCase("学生")) {
            userId = userService.save(new Student(user));
        } else {
            result.rejectValue("category", "用户类型为教师或者学生");
        }
        if(result.hasErrors()) {
            return "redirect:/auth/login";
        }
        return "redirect:/admin";
    }
    //TODO 邮箱验证，找回密码

}