package edu.hust.itec.dao;

import edu.hust.itec.model.News;
import edu.hust.itec.model.NewsCategory;
import java.util.*;

public interface NewsDAO {
    List<News> getList(List<Integer> categoryIds, int start, int pageSize);

    int getNumberOfItems(List<Integer> categoryIds);

    News getItemById(int id);

    NewsCategory getCategoryByName(String categoryName);
}
