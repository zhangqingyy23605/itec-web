package edu.hust.itec.dao;

import edu.hust.itec.model.News;
import edu.hust.itec.model.NewsCategory;
import java.util.*;

public interface NewsDAO extends BaseDAO<News, Integer> {
    //category
    NewsCategory getCategoryByName(String categoryName);
}
