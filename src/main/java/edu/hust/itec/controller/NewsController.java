package edu.hust.itec.controller;

import edu.hust.itec.model.Category;
import edu.hust.itec.model.News;
import edu.hust.itec.service.NewsService;
import edu.hust.itec.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    static final private String rootCategoryName = "新闻中心";
    static final private String url = "/news";

    @PostConstruct
    public void initCategoryTree() {
        this.newsService.initCategoryTree(this.rootCategoryName);
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

    //新闻列表
    @RequestMapping
    public String getList(Page page, ModelMap model
            ,@RequestParam(required = false) String categoryName) {

        if(categoryName != null) {
            page.setPageNumber(1);
        }

        Collection<News> newsList = this.newsService.getByCategory(page);
        model.addAttribute("newsList", newsList);

        Category rootCategory = this.newsService.getRootCategory();
        model.addAttribute("rootCategory", rootCategory);
        return "/news/list";
    }

    //新闻正文
    @RequestMapping(value = "/{newsId}", method = RequestMethod.GET)
    public String getItem(@PathVariable int newsId, ModelMap model) {
        News news = this.newsService.getById(newsId);
        model.addAttribute("news", news);
        return "/news/item";
    }

    //删除新闻
    @RequestMapping(value = "/{newsId}", method = RequestMethod.DELETE)
    public String deleteItem(@PathVariable int newsId) {
        this.newsService.deleteById(newsId);
        return "redirect:/news";
    }

    //表单页面
    @RequestMapping("/input")
    public String inputView(ModelMap model) {
        model.addAttribute("categoryList", this.newsService.getCategoryLeaves());
        return "news/input";
    }
    //添加新闻
    @RequestMapping("/add")
    public String addItemView(ModelMap model) {
        if(!model.containsAttribute("news")) {//首次添加
            model.addAttribute("news", new News());
        }
        return "forward:/news/input";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String addItem(@Valid News news, BindingResult result, RedirectAttributes redirectAttributes) {
        if (news.getEditor().getId() == null) {
            result.rejectValue("editor.id", "editor is null", "作者不能为空，请先登录");
        }
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "news", result);
            redirectAttributes.addFlashAttribute("news", news);
            return "redirect:/news/add";
        }
        return "redirect:/news/" + this.newsService.save(news);
    }

    //编辑新闻
    @RequestMapping(value = "/{newsId}/edit", method = RequestMethod.GET)
    public String editItemView(@PathVariable Integer newsId, ModelMap model) {
        if(!model.containsAttribute("news")) {//进入编辑
            model.addAttribute("news", this.newsService.getById(newsId));
        }
        return "forward:/news/input";
    }
    @ModelAttribute
    public void getNews(@RequestParam(value = "id", required = false) Integer newsId, ModelMap model) {
        if(newsId != null) {//是修改操作
            News news = this.newsService.getById(newsId);
            model.addAttribute("news", news);
        }
    }
    @RequestMapping(method = RequestMethod.PUT)
    public String editItem(@Valid News news, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "news", result);
            redirectAttributes.addFlashAttribute("news", news);
            return "redirect:/news/" + news.getId() + "/edit";
        }
        Category newCateogyry = new Category();
        newCateogyry.setId(news.getCategory().getId());
        news.setCategory(newCateogyry);
        this.newsService.update(news);
        return "redirect:/news/" + news.getId();
    }

//    @InitBinder
//    private void registerPropertyEditor(DataBinder binder) {
//        binder.registerCustomEditor(News.class, "category.id", new NewsPropertyEditor(this.newsService));
//    }

    //练习用
    @RequestMapping("/uploadfile")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {//如果用户没有选择本地文件就点击上传，会检出isEmpty
            file.transferTo(new File("d:/" + file.getOriginalFilename()));
            System.out.println("文件已保存");
        }
        return "redirect:/news/add";
    }

}