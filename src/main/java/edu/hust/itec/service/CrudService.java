package edu.hust.itec.service;

import edu.hust.itec.model.Category;
import edu.hust.itec.util.Page;
import java.util.*;

/**
 * Created by xsh on 2015/5/13.
 */

// Create, Retrieve, Update and Delete
public interface CrudService<T> {
    //item
    Integer save(T t);

    boolean deleteById(Integer id);

    void update(T t);

    T getById(Integer id);

    //list
    Collection<T> getByCategory(Page page);

    //category
    void initCategoryTree(String rootCategoryName);

    Category getCateogyrByName(String categoryName);

    Category getRootCategory();//获得左侧导航栏

    Collection<Category> getCategoryLeaves();//添加条目时，需要知道所有叶子分类
}