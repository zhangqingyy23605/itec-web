package edu.hust.itec.controller;

import edu.hust.itec.model.News;
import edu.hust.itec.service.NewsService;
import edu.hust.itec.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by xsh on 2015/4/30.
 */

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private NewsService newsService;

    @ModelAttribute
    public void preparePage(HttpSession session){
        session.removeAttribute("page");
    }

    @RequestMapping
    public String index(ModelMap model) {
        Page page = new Page();
        page.setCategoryName("通知公告");
        page.setPageSize(10);
        Collection<News> newsListLeft = newsService.getList(page);
        model.addAttribute("newsListLeft", newsListLeft);

        page.setCategoryName("热点新闻");
        page.setPageSize(6);
        Collection<News> newsListRight = newsService.getList(page);
        model.addAttribute("newsListRight", newsListRight);
        return "index";
    }

    //产生异常
    @RequestMapping("/error")
    public String makeError() {
//        throw new MyException();
        int a = 1/0;
        return "redirect:/news";
    }
}