package edu.hust.itec.service.impl;

import edu.hust.itec.dao.NewsDAO;
import edu.hust.itec.model.News;
import edu.hust.itec.service.NewsService;
import edu.hust.itec.util.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service("newsService")
public class NewsServiceImpl implements NewsService {
    @Resource
    public NewsDAO newsDAO;

    public List<News> getNewsList(Page page) {
        String categoryName = page.getCategoryName();
        int numberOfRecords = newsDAO.getNumberOfRecords(categoryName);
        page.setNumberOfRecordsAndPages(numberOfRecords);
        //得知有多少页后，才能知道当前访问的页码是否有问题，并且从数据库第几条开始查询
        int firstResult = page.getFirstResult();
        int pageSize = page.getPageSize();
        return newsDAO.getNewsList(categoryName, firstResult, pageSize);
    }

    public News getNewsById(int id) {
        //newsDAO.addVisitTimeById(id);
        return newsDAO.getNewsById(id);
    }

    //getter and setter
    public void setNewsDAO(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }
}
