package edu.hust.itec.controller;

import edu.hust.itec.model.Teacher;
import edu.hust.itec.model.User;
import edu.hust.itec.service.UserService;
import edu.hust.itec.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by xsh on 2015/4/30.
 */

@Controller
@RequestMapping("/admin")
@SessionAttributes("auth")
public class AdminController {
    @Autowired
    private UserService userService;

    static final private int pageSize = 8;
    static final private String rootCategoryName = "管理后台";
    static final private String url = "/admin";

    @ModelAttribute
    public Page preparePage(HttpSession session) {
        Page page = (Page) session.getAttribute("page");
        if (page == null || page.getUrl() != this.url) {
            page = new Page();
            page.setPageSize(this.pageSize);
            page.setUrl(this.url);
            page.setCategoryName(this.rootCategoryName);
            session.setAttribute("page", page);
        }
        return page;
    }

    @ModelAttribute("auth")
    public User prepareAuthUser(HttpSession session) {
        User authUser = (User) session.getAttribute("auth");
        return authUser;
    }
    @RequestMapping(value="", method = RequestMethod.GET)
    public String route(@ModelAttribute("auth") User authUser) {
        return "redirect:/admin/profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)//左侧栏导航到自己的个人信息编辑页面
    public String profileEditView(@ModelAttribute("auth") User authUser) {
        return "redirect:/admin/user/" + authUser.getId() + "/edit";
    }

}