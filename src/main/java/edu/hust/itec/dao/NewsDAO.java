package edu.hust.itec.dao;

import edu.hust.itec.model.News;

import java.util.List;

public interface NewsDAO {
    List<News> getNewsList(String categoryName, int start, int pageSize);

    int getNumberOfRecords(String categoryName);

    News getNewsById(int id);
}
