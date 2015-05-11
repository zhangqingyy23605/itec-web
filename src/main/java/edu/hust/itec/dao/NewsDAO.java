package edu.hust.itec.dao;

import edu.hust.itec.model.News;
import edu.hust.itec.model.NewsCategory;
import java.util.*;

public interface NewsDAO {
    //list
    List<News> getList(List<Integer> categoryIds, int start, int pageSize);
    int getNumberOfItems(List<Integer> categoryIds);

    //item
    News getItemById(int id);
    void addItem(News news);
    void deleteItemById(int id);
    void updateItem(News news);

    //category
    NewsCategory getCategoryByName(String categoryName);
}
