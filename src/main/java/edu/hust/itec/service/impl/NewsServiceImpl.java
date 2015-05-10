package edu.hust.itec.service.impl;

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
        NewsCategory newsCategory = categoryMap.get(categoryName);
        if (newsCategory == null) {//如果参数错误，就用默认值
            newsCategory = categoryMap.get(page.getColumnName());
        }
        List<Integer> categoryIds = newsCategory.getLeavesId();
        int numberOfRecords = newsDAO.getNumberOfItems(categoryIds);
        page.setNumberOfRecordsAndPages(numberOfRecords);
        //需要先知道有多少页后，才能知道当前访问的页码是否有问题，并且从数据库第几条开始查询
        int firstResult = page.getFirstResult();
        int pageSize = page.getPageSize();
        return newsDAO.getList(categoryIds, firstResult, pageSize);
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
            System.out.println("\"" + columnName + "\"分类初始化完成");
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
                int leafId = currentCategory.getId();

                NewsCategory upperCategory = currentCategory;
                upperCategory.addLeafId(leafId);

                while(upperCategory.getParent() != null) {
                    upperCategory = upperCategory.getParent();
                    upperCategory.addLeafId(leafId);
                }
            } else {
                for (NewsCategory subRootNewsCategory : children) {
                    queue.add(subRootNewsCategory);
                }
            }
        }
    }

    //getter and setter
    public void setNewsDAO(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }
}
