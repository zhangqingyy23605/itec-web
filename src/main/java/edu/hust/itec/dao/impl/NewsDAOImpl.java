package edu.hust.itec.dao.impl;

import edu.hust.itec.dao.NewsDAO;
import edu.hust.itec.model.News;
import edu.hust.itec.model.NewsCategory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository("newsDAO")
public class NewsDAOImpl implements NewsDAO {
    @Resource
    private SessionFactory sessionFactory;

    public void saveLeaves(NewsCategory rootNewsCategory, List<NewsCategory> category_leaves) {
        if (rootNewsCategory == null) { //TODO 名称不全有bug
            return;
        }
        else if (rootNewsCategory.getChildren().isEmpty()) {
            category_leaves.add(rootNewsCategory);
        } else {
            for (NewsCategory subrootNewsCategory : rootNewsCategory.getChildren() ) {
                saveLeaves(subrootNewsCategory, category_leaves);
            }
        }
    }

    public List<News> getNewsList(String categoryName, int firstResult, int pageSize) {
        Session session = this.sessionFactory.getCurrentSession();

        Query query = session.createQuery("select nc from NewsCategory nc where name=:name");
        query.setString("name", categoryName);
        NewsCategory rootNewsCategory = (NewsCategory)query.uniqueResult();
        List<NewsCategory> category_leaves = new ArrayList<>();
        this.saveLeaves(rootNewsCategory, category_leaves);


        Query query2 = session.createQuery(
                "select new News(news.heading, news.addDate, news.id) from News news " +
                        "where news.category in :category_leaves order by news.addDate desc");
        query2.setParameterList("category_leaves", category_leaves);
        //性能极低
//        Query query2 = session.createQuery("select new News(news.heading, news.addDate) from News news");
        query2.setFirstResult(firstResult);
        query2.setMaxResults(pageSize);
        List<News> newsList = (List<News>)query2.list();
        return newsList;
    }

    public int getNumberOfRecords(String categoryName) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("select nc from NewsCategory nc where name=:name");
        query.setString("name", categoryName);
        NewsCategory rootNewsCategory = (NewsCategory)query.uniqueResult();
        List<NewsCategory> category_leaves = new ArrayList<>();
        this.saveLeaves(rootNewsCategory, category_leaves);
        Query query2 = session.createQuery("select count(*) from News news where news.category in :category_leaves");
        query2.setParameterList("category_leaves", category_leaves);
        int numberOfNews = ((Long)query2.uniqueResult()).intValue();

//        Query query = session.createQuery("select count(*) from News news");
//        int numberOfNews = ((Long)query.uniqueResult()).intValue();
        return numberOfNews;
    }

    public News getNewsById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (News)session.get(News.class, id);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
