package edu.hust.itec.controller;

import edu.hust.itec.model.Category;
import edu.hust.itec.model.News;
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

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by xsh on 2015/4/30.
 */

@Controller
@RequestMapping("/admin/user")
@SessionAttributes("auth")
public class UserController {
    @Autowired
    private UserService userService;

    static final private int pageSize = 8;
    static final private String rootCategoryName = "用户管理";
    static final private String url = "/admin/user";

    @PostConstruct
    public void initCategoryTree() {
        this.userService.initCategoryTree(this.rootCategoryName);
    }

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


    //用户列表
    @RequestMapping(value="", method = RequestMethod.GET)
    public String getList(Page page, ModelMap model
            ,@RequestParam(required = false) String categoryName) {

        if(categoryName != null) {
            page.setPageNumber(1);
        }

        Collection<User> userList = this.userService.getByCategory(page);
        model.addAttribute("userList", userList);

        return "/user/list";
    }


    @RequestMapping(value = "/{userId}/edit", method = RequestMethod.GET)
    public String userEditView(@PathVariable Integer userId, ModelMap model) {
        User user;
        if(!model.containsAttribute("user")) {//首次更新
            user = this.userService.getById(userId);
            model.addAttribute("user", user);
        } else {
            user = (User)model.get("user");
        }
        Category c = user.getCategory();
        c = c.getParent();
        String categoryName = c.getName();

//        if (user.getCategory().getParent().getName().equalsIgnoreCase("学生")) {
            Collection<Teacher> teachers = this.userService.getTeachers();
            model.addAttribute("teachers", teachers);
//        }

        model.addAttribute("categoryList", this.userService.getCategoryLeaves());
        return "/user/profile";
    }
    @ModelAttribute
    public void getNews(@RequestParam(value = "id", required = false) Integer userId, ModelMap model) {
        if(userId != null) {//是修改操作
            model.addAttribute("user", this.userService.getById(userId));
        }
    }
    @RequestMapping(value = "", method = RequestMethod.PUT)
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