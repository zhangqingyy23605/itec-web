package edu.hust.itec.dao;

import edu.hust.itec.model.Category;
import edu.hust.itec.util.Page;
import java.util.*;

public interface CrudDAO<T> {
    //item
    Integer save(T t);
    boolean deleteById(Integer id);
    void update(T t);
    T getById(Integer id);

    //list
    Collection<T> getByCategory(Page page);

    //category
    void initCategoryTree(String rootCategoryName);
    Category getRootCategory();
    Category getCategoryByName(String categoryName);
    Collection<Category> getCategoryLeaves();
}
