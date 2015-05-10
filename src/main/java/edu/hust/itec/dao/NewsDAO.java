package edu.hust.itec.dao;

import edu.hust.itec.model.News;
import edu.hust.itec.model.NewsCategory;
import java.util.*;

public interface NewsDAO {
    List<News> getList(String categoryName, int start, int pageSize);

    int getNumberOfRecords(String categoryName);

    News getItemById(int id);

    NewsCategory getCategoryByName(String categoryName);
}
