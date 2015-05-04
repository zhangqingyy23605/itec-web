package edu.hust.itec.service;

import edu.hust.itec.model.News;
import edu.hust.itec.model.NewsCategory;
import edu.hust.itec.util.Page;

import java.util.List;

public interface NewsService {
    List<News> getNewsList(Page page);

    News getNewsById(int id);
}
