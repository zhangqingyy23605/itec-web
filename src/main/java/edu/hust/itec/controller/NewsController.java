package edu.hust.itec.controller;

import edu.hust.itec.model.News;
import edu.hust.itec.service.NewsService;
import edu.hust.itec.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by xsh on 2015/4/30.
 */

@Controller
@RequestMapping("/news")
@SessionAttributes({"page"})
public class NewsController {
    @Autowired
    private NewsService newsService;

    static final int initPageNumber = 1;
    static final private int pageSize = 8;
    static final private String columnName = "新闻中心";
//    static private NewsCategory rootCategory;
//    static {
//        System.out.println("记载Action");
//        rootCategory = newsAction.newsService.getCategoryTree(columnName);
        //循环，放入Map中
//    }

    @ModelAttribute("page")
    public Page initPage() {
        Page page = new Page();
        page.setPageSize(this.pageSize);
        page.setColumnName(this.columnName);
        page.setCategoryName(this.columnName);
        page.setPageNumber(this.initPageNumber);
        return page;
    }

    @RequestMapping
    public String newsList(@ModelAttribute Page page, ModelMap model) {
            List<News> newsList = this.newsService.getNewsList(page);
            model.addAttribute("newsList", newsList);
            return "/news/list";
    }
    @RequestMapping(params = "categoryName")//入口
    public String chooseCategory(ModelMap model, @RequestParam String categoryName) {
        Page page = this.initPage();
        page.setCategoryName(categoryName);
        model.addAttribute("page", page);//无视Session中是否已经存在page，直接放一个新的page
        return "redirect:/news";
    }
    @RequestMapping(params = {"pageAction", "!categoryName", })
    public String changePageAction(@ModelAttribute Page page, @RequestParam String pageAction) {
        page.setPageAction(pageAction);
        return "redirect:/news";
    }
    @RequestMapping(params = {"pageNumber", "!categoryName", "!pageAction"})
    public String changePageNumber(@ModelAttribute Page page, @RequestParam int pageNumber) {
        page.setPageNumber(pageNumber);
        return "redirect:/news";
    }

    @RequestMapping("/{newsId}")
    public String newsItem(ModelMap model, @PathVariable int newsId) {
        News newsItem = this.newsService.getNewsById(newsId);
        model.addAttribute("newsItem", newsItem);
        return "/news/item";
    }
}