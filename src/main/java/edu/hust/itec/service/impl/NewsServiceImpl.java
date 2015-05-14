package edu.hust.itec.service.impl;
import edu.hust.itec.dao.NewsDAO;
import edu.hust.itec.model.News;
import edu.hust.itec.model.Category;
import edu.hust.itec.service.NewsService;
import edu.hust.itec.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Transactional
@Service("newsService")
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDAO newsDAO;

    //item
    public boolean saveOrUpdate(News news) {
        //news.setModifiedTime(new Date());
        return this.newsDAO.saveOrUpdate(news);
    }
    public boolean deleteById(Integer id) {
        return this.newsDAO.deleteById(id);
    }
    public News getById(Integer id) {
        //this.newsDAO.addTimesOfVisitById(id);
        return this.newsDAO.getById(id);
    }

    //list
    public Collection<News> getByCategory(Page page) {
        return newsDAO.getByCategory(page);
    }

    //category========================================================
    public void initCategoryTree(String rootCategoryName) {
        this.newsDAO.initCategoryTree(rootCategoryName);
    }

    public Category getRootCategory() {
        return this.newsDAO.getRootCategory();
    }

    public Collection<Category> getCategoryLeaves() {
        return this.newsDAO.getCategoryLeaves();
    }
    //category========================================================

    //getter and setter
    public void setNewsDAO(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }
}
