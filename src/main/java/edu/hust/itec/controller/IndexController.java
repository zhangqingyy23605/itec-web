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
import java.util.List;

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
        List<News> newsListLeft = newsService.getNewsList(page);
        model.addAttribute("newsListLeft", newsListLeft);

        page.setCategoryName("热点新闻");
        page.setPageSize(6);
        List<News> newsListRight = newsService.getNewsList(page);
        model.addAttribute("newsListRight", newsListRight);
        return "index";
    }
}