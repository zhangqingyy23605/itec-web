package edu.hust.itec.dao;

import edu.hust.itec.model.Category;
import edu.hust.itec.util.Page;
import java.util.*;

public interface CrudDAO<T> {
    //item
    boolean saveOrUpdate(T t);
    boolean deleteById(Integer id);
    T getById(Integer id);

    //list
    Collection<T> getByCategory(Page page);

    //category
    void initCategoryTree(String rootCategoryName);
    Category getRootCategory();
    Collection<Category> getCategoryLeaves();
}
