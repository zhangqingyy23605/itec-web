package edu.hust.itec.dao.impl;

import edu.hust.itec.dao.NewsDAO;
import edu.hust.itec.model.News;
import edu.hust.itec.model.NewsCategory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.*;

@Repository("newsDAO")
public class NewsDAOImpl implements NewsDAO {
    @Resource
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<News> getList(List<Integer> categoryIds, int firstResult, int pageSize) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery(
                "select new News(news.heading, news.addDate, news.id) from News news " +
                        "where news.category.id in :categoryIds order by news.addDate desc");
        query.setParameterList("categoryIds", categoryIds);
        query.setFirstResult(firstResult);
        query.setMaxResults(pageSize);
        return (List<News>)query.list();
    }

    public int getNumberOfItems(List<Integer> categoryIds) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from News news where news.category.id in :categoryIds");
        query.setParameterList("categoryIds", categoryIds);
        return ((Long)query.uniqueResult()).intValue();
    }

    public News getItemById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (News)session.get(News.class, id);
    }

    //取得该分类和他的子树，以及每个子分类对应的所有叶子
    public NewsCategory getCategoryByName(String categoryName) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("select nc from NewsCategory nc where name=:name");
        query.setString("name", categoryName);
        return (NewsCategory)query.uniqueResult();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
