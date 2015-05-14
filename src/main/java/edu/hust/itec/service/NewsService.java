package edu.hust.itec.service;

import edu.hust.itec.model.News;
import edu.hust.itec.model.NewsCategory;
import edu.hust.itec.util.Page;
import java.util.*;

public interface NewsService extends BaseService<News, Integer> {
    //Category
    void initCategoryMap(String columnName);
    List<NewsCategory> getCategoryLeaves();
}
