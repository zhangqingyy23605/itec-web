package edu.hust.itec.service.impl;

import edu.hust.itec.dao.NewsDAO;
import edu.hust.itec.model.News;
import edu.hust.itec.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class NewsServiceImpl extends CrudServiceImpl<News, NewsDAO> implements NewsService {
    //item
    public void update(News news) {
        //news.setModifiedTime(new Date());
        super.update(news);
    }
    public News getById(Integer id) {
        //this.newsDAO.addTimesOfVisitById(id);
        return super.getById(id);
    }

    @Autowired
    public void setSomeDAO(NewsDAO someDAO) {
        this.someDAO = someDAO;
    }
}
