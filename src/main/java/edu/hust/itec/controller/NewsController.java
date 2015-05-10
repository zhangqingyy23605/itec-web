package edu.hust.itec.controller;

import edu.hust.itec.model.News;
import edu.hust.itec.service.NewsService;
import edu.hust.itec.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by xsh on 2015/4/30.
 */

@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    static final private int pageSize = 8;
    static final private String columnName = "新闻中心";

    @PostConstruct
    public void initCategoryMap() {
        newsService.initCategoryMap(this.columnName);
    }

    @ModelAttribute
    public Page preparePage(HttpSession session){
        Page page = (Page)session.getAttribute("page");
        if (page == null || page.getColumnName() != columnName) {
            page = new Page();
            page.setPageSize(this.pageSize);
            page.setColumnName(this.columnName);
            page.setCategoryName(this.columnName);
            session.setAttribute("page", page);
        }
        return page;
    }

    @RequestMapping
    public String getList(@ModelAttribute Page page, ModelMap model) {
            List<News> newsList = this.newsService.getList(page);
            model.addAttribute("newsList", newsList);
            return "/news/list";
    }
    @RequestMapping(params = "categoryName")
    public String chooseCategory(@ModelAttribute Page page, @RequestParam String categoryName) {
        page.setCategoryName(categoryName);
        page.setPageNumber(1);
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

    @RequestMapping(value = "/{newsId}", method = RequestMethod.GET)
    public String getItem(@PathVariable int newsId, ModelMap model) {
        News news = this.newsService.getItemById(newsId);
        model.addAttribute("news", news);
        return "/news/item";
    }

    @RequestMapping("/add")
    public String addItemView(ModelMap model) {
        model.addAttribute("categoryList", newsService.getCategoryList());
        model.addAttribute("news", new News());//TODO news editor 由改为 User类型的 editor_id
        return "news/item_input";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addItem(News news) {
        newsService.addItem(news);
        return "redirect:/news";
    }

    @RequestMapping(value = "/{newsId}", method = RequestMethod.DELETE)
    public String deleteItem(@PathVariable int newsId) {
        this.newsService.deleteItemById(newsId);
        return "redirect:/news";
    }

//    @ModelAttribute
//    public News prepareNews(@PathVariable int newsId){
//        News news = newsService.getItemById(newsId);
//        return news;
//    }
//
//    @RequestMapping("/{newsId}/edit")
//    public String editItem(@ModelAttribute News news) {
//        //放入已有数据
//        return "/news/item_edit";
//    }
}