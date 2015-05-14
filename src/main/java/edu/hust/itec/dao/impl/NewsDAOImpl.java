package edu.hust.itec.dao.impl;

import edu.hust.itec.dao.NewsDAO;
import edu.hust.itec.model.News;
import edu.hust.itec.util.Page;
import org.springframework.stereotype.Component;

import java.util.Collection;


@Component("newsDAO")
public class NewsDAOImpl extends CrudDAOImpl<News,Integer> implements NewsDAO {

    public Collection<News> getByCategory(Page page) {
        return super.getByCategory(page,
                "new News(t.id, t.heading, t.createTime)",
                "order by t.createTime desc");
    }

}
