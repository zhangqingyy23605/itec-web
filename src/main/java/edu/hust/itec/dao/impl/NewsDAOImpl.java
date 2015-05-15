package edu.hust.itec.dao.impl;

import edu.hust.itec.dao.NewsDAO;
import edu.hust.itec.model.News;
import edu.hust.itec.util.Page;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public class NewsDAOImpl extends CrudDAOImpl<News> implements NewsDAO {

    public Collection<News> getByCategory(Page page) {
        return super.getByCategory(page,
                "new News(t.id, t.heading, t.createTime)",
                "order by t.createTime desc");
    }

}
