package edu.hust.itec.service;

import edu.hust.itec.model.News;
import edu.hust.itec.model.NewsCategory;
import edu.hust.itec.util.Page;
import java.util.*;

public interface NewsService {

    //News
    List<News> getList(Page page);

    News getItemById(int id);

//    void addItem(News news);

    //Category
    void initCategoryMap(String columnName);
    List<NewsCategory> getCategoryList();
}
