package edu.hust.itec.service;

import edu.hust.itec.util.Page;
import java.io.Serializable;
import java.util.*;

/**
 * Created by xsh on 2015/5/13.
 */
public interface BaseService<T, ID extends Serializable> {
    //item
    boolean saveOrUpdate(T t);
    boolean deleteById(ID id);
    T getById(ID id);

    //list
    Collection<T> getList(Page page);
}
