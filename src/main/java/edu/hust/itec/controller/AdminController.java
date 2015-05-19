package edu.hust.itec.controller;

import edu.hust.itec.model.News;
import edu.hust.itec.model.User;
import edu.hust.itec.service.NewsService;
import edu.hust.itec.service.UserService;
import edu.hust.itec.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
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
    static final private String rootCategoryName = "后台管理";
    static final private String url = "/admin";

//    @PostConstruct
//    public void initCategoryTree() {
//        this.adminService.initCategoryTree(this.rootCategoryName);
//    }

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

    //TODO 拦截器：确保已经登陆且有权限
    @ModelAttribute("auth")
    public User prepareAuthUser(HttpSession session) {
        User authUser = (User) session.getAttribute("auth");
        return authUser;
    }
    @RequestMapping
    public String profile(User auth, ModelMap model) {
        User.Type.TEACHER.toString();
        return "admin/profile";
    }
}