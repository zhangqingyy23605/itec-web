package edu.hust.itec.dao;

import edu.hust.itec.model.Category;
import edu.hust.itec.util.Page;
import java.io.Serializable;
import java.util.*;

public interface CrudDAO<T, ID extends Serializable> {
    //item
    boolean saveOrUpdate(T t);
    boolean deleteById(ID id);
    T getById(ID id);

    //list
    Collection<T> getByCategory(Page page);

    //category
    void initCategoryTree(String rootCategoryName);
    Category getRootCategory();
    Collection<Category> getCategoryLeaves();
}
