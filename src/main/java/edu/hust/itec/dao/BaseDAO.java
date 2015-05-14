package edu.hust.itec.dao;

import java.io.Serializable;
import java.util.*;

public interface BaseDAO<T, ID extends Serializable> {
    //item
    boolean saveOrUpdate(T t);
    boolean deleteById(ID id);
    T getById(ID id);

    //list
    Collection<T> getByCategory(Collection<Integer> categoryIds, int start, int pageSize);
    int getTotalNumberByCategory(Collection<Integer> categoryIds);
}
