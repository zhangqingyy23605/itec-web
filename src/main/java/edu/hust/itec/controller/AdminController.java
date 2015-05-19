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

//    @Autowired
//    private CategoryDAOImpl categoryDAO;

    static final private int pageSize = 8;
    static final private String rootCategoryName = "管理后台";
    static final private String url = "/admin";

//    @PostConstruct
//    public void initCategoryTree() {
//        this.categoryDAO.initCategoryTree(this.rootCategoryName);
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
        return "redirect:/admin/user/" + authUser.getId() + "/edit";//null???!!!!!!
    }
    @RequestMapping(value = "/user/{userId}/edit", method = RequestMethod.GET)
    public String userEditView(@PathVariable Integer userId, ModelMap model) {
        User user;
        if(!model.containsAttribute("user")) {//首次更新
            user = (User)this.userService.getById(userId);
            model.addAttribute("user", user);
        } else {
            user = (User)model.get("user");
        }
        if (user.getType().equalsIgnoreCase("STUDENT")) {
            Collection<Teacher> teachers = this.userService.getTeachers();
            model.addAttribute("teachers", teachers);
        }
        return "/user/profile";
    }
    @ModelAttribute
    public void getNews(@RequestParam(value = "id", required = false) Integer userId, ModelMap model) {
        if(userId != null) {//是修改操作
            model.addAttribute("user", this.userService.getById(userId));
        }
    }
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public String userEdit(@Valid User user, BindingResult result, RedirectAttributes redirectAttributes, ModelMap model) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "user", result);
            redirectAttributes.addFlashAttribute("user", user);
        } else {
            this.userService.update(user);
        }
        return "redirect:/admin/user/" + user.getId() + "/edit";
    }
}