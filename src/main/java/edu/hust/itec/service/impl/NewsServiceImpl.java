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

    //list
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

    //item
    public News getItemById(int id) {
        //newsDAO.addVisitTimeById(id);
        return newsDAO.getItemById(id);
    }
    public void addItem(News news) {
        newsDAO.addItem(news);
    }
    public void deleteItemById(int id) {
        newsDAO.deleteItemById(id);
    }

    //Category
    private Map<String, NewsCategory> categoryMap = new HashMap<>();
    private List<NewsCategory> categoryList = new ArrayList<>();
    public void initCategoryMap(String columnName) {
        System.out.println("开始加载\"" + columnName + "\"分类信息");
        NewsCategory rootNewsCategory = newsDAO.getCategoryByName(columnName);
        if (rootNewsCategory == null) {
            System.out.println("数据库中不存在\"" + columnName + "\"的分类信息！");
        } else {
            extractLeavesToCategoryMap(rootNewsCategory, this.categoryMap);
            extractLeavesToCategoryList(this.categoryMap, this.categoryList);
            System.out.println("成功加载\"" + columnName + "\"分类信息");
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
    private void extractLeavesToCategoryList(Map<String, NewsCategory> categoryMap, List<NewsCategory> categoryList) {
        for (NewsCategory newsCategory: this.categoryMap.values()) {
            if (newsCategory.getChildren().isEmpty()) {
                this.categoryList.add(newsCategory);
            }
        }
    }

    public List<NewsCategory> getCategoryList() {
        return this.categoryList;
    }

    //getter and setter
    public void setNewsDAO(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }
}
