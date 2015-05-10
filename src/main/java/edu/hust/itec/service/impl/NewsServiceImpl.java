package edu.hust.itec.service.impl;

import com.sun.jmx.remote.internal.ArrayQueue;
import edu.hust.itec.dao.NewsDAO;
import edu.hust.itec.model.News;
import edu.hust.itec.model.NewsCategory;
import edu.hust.itec.service.NewsService;
import edu.hust.itec.util.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.*;

@Transactional
@Service("newsService")
public class NewsServiceImpl implements NewsService {
    @Resource
    private NewsDAO newsDAO;

    //News
    public List<News> getList(Page page) {
        String categoryName = page.getCategoryName();
        int numberOfRecords = newsDAO.getNumberOfRecords(categoryName);
        page.setNumberOfRecordsAndPages(numberOfRecords);
        //需要先知道有多少页后，才能知道当前访问的页码是否有问题，并且从数据库第几条开始查询
        int firstResult = page.getFirstResult();
        int pageSize = page.getPageSize();
        return newsDAO.getList(categoryName, firstResult, pageSize);
    }

    public News getItemById(int id) {
        //newsDAO.addVisitTimeById(id);
        return newsDAO.getItemById(id);
    }

    //Category
    private Map<String, NewsCategory> categoryMap = new HashMap<>();

    public void initCategoryMap(String columnName) {
        NewsCategory rootNewsCategory = newsDAO.getCategoryByName(columnName);
        if (rootNewsCategory == null) {
            System.out.println("数据库中不存在\"" + columnName + "\"栏目的分类信息！");
        } else {
            extractLeavesToCategoryMap(rootNewsCategory, this.categoryMap);
        }
    }
    private void extractLeavesToCategoryMap(NewsCategory rootCategory, Map<String, NewsCategory> categoryMap) {
        Queue<NewsCategory> queue = new LinkedList<>();
        queue.add(rootCategory);
        NewsCategory currentCategory;

        while (!queue.isEmpty()) {
            currentCategory = queue.remove();
            categoryMap.put(currentCategory.getName(), currentCategory);

            List<NewsCategory> children = currentCategory.getChildren();
            if (children.isEmpty()) {
                //如果某个节点没有孩子，就用parent向上找所有的祖先，放入他们的leaves中。
                String leafName = currentCategory.getName();

                NewsCategory upperCategory = currentCategory;
                upperCategory.addLeavesName(leafName);

                while(upperCategory.getParent() != null) {
                    upperCategory = upperCategory.getParent();
                    upperCategory.addLeavesName(leafName);
                }
            } else {
                for (NewsCategory subRootNewsCategory : children) {
                    queue.add(subRootNewsCategory);
                }
            }
        }

        System.out.println("分类树完成");

    }

    //getter and setter
    public void setNewsDAO(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }
}
