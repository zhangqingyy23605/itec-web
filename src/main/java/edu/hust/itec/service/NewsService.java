package edu.hust.itec.service;

import edu.hust.itec.model.News;
import edu.hust.itec.model.NewsCategory;
import edu.hust.itec.util.Page;
import java.util.*;

public interface NewsService {

    //list
    List<News> getList(Page page);

    //item
    News getItemById(int id);
    void addItem(News news);
    void deleteItemById(int id);
    void updateItem(News news);

    //Category
    void initCategoryMap(String columnName);
    List<NewsCategory> getCategoryList();
}
