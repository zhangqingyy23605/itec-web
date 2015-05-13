package edu.hust.itec.controller;

import edu.hust.itec.model.News;
import edu.hust.itec.service.NewsService;
import edu.hust.itec.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.PostConstruct;
import javax.servlet.http.*;
import javax.validation.Valid;
import java.io.*;
import java.util.*;

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

    //新闻列表
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

    //新闻正文
    @RequestMapping(value = "/{newsId}", method = RequestMethod.GET)
    public String getItem(@PathVariable int newsId, ModelMap model) {
        News news = this.newsService.getItemById(newsId);
        model.addAttribute("news", news);
        return "/news/item";
    }

    //删除新闻
    @RequestMapping(value = "/{newsId}", method = RequestMethod.DELETE)
    public String deleteItem(@PathVariable int newsId) {
        this.newsService.deleteItemById(newsId);
        return "redirect:/news";
    }

    //添加新闻
    @RequestMapping("/add")
    public String addItemView(ModelMap model) {
        model.addAttribute("categoryList", newsService.getCategoryLeaves());
        model.addAttribute("news", new News());//TODO news editor 由改为 User类型的 editor_id：做用户管理的时候改进
        return "news/input";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String addItem(@Valid News news, BindingResult result, ModelMap model) {
        if(result.getErrorCount() > 0) {
//            for(FieldError error: result.getFieldErrors()) {
//                System.out.println(error.getField() + ": " + error.getDefaultMessage());
//            }
            model.addAttribute("categoryList", newsService.getCategoryLeaves());
            return "news/input";
            //TODO 错误回显不够优雅：先解决“编辑新闻”的回显问题，看需不需要改变回显机制。目前依赖于Spring的Form标签。
        } else {
            newsService.addItem(news);
            return "redirect:/news";
        }
    }

    //编辑新闻
    @RequestMapping(value = "/{newsId}/edit", method = RequestMethod.GET)
    public String editItemView(@PathVariable int newsId, ModelMap model) {
        //放入已有数据
        model.addAttribute("news", newsService.getItemById(newsId));
        model.addAttribute("categoryList", newsService.getCategoryLeaves());
        return "news/input";
    }
    @ModelAttribute//这里的RequestParam包含POST报文中的Param
    public void getNews(@RequestParam(value = "id", required = false) Integer newsId, ModelMap model) {
        if(newsId != null) {//有id是修改操作
            model.addAttribute("news", this.newsService.getItemById(newsId));
        }
    }
    @RequestMapping(method = RequestMethod.PUT)
    public String editItem(@Valid News news, BindingResult result, ModelMap model) {
        if(result.getErrorCount() > 0) {
            model.addAttribute("categoryList", newsService.getCategoryLeaves());
//            model.addAttribute("news", news);
            return "news/input";
        } else {
            newsService.updateItem(news);
            return "redirect:/news/" + news.getId();
            //TODO 错误回显报错：
        }
    }

    //练习用
    @RequestMapping("/uploadfile")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        if(!file.isEmpty()) {//如果用户没有选择本地文件就点击上传，会检出isEmpty
            file.transferTo(new File("d:/" + file.getOriginalFilename()));
            System.out.println("文件已保存");
        }
        return "redirect:/news/add";
    }

}