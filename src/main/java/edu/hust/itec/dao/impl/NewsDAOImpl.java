package edu.hust.itec.dao.impl;

import edu.hust.itec.dao.NewsDAO;
import edu.hust.itec.model.News;
import edu.hust.itec.model.NewsCategory;
import org.hibernate.*;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository("newsDAO")
public class NewsDAOImpl extends AbstractDAOImpl<News, Integer> implements NewsDAO {
    //list
    //@SuppressWarnings("unchecked")
    public Collection<News> getByCategory(Collection<Integer> categoryIds, int firstResult, int pageSize) {
        return super.getByCategory(categoryIds, firstResult, pageSize,
                "new News(t.id, t.heading, t.addDate)",
                "order by t.addDate desc");
    }

    //category
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
