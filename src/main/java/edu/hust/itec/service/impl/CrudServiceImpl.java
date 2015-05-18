package edu.hust.itec.service.impl;

import edu.hust.itec.dao.CrudDAO;
import edu.hust.itec.model.Category;
import edu.hust.itec.service.CrudService;
import edu.hust.itec.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional
public abstract class CrudServiceImpl<T, SomeDAO extends CrudDAO<T>> implements CrudService<T> {
    //item
    public Integer save(T t) {
        return this.someDAO.save(t);
    }
    public boolean deleteById(Integer id) {
        return this.someDAO.deleteById(id);
    }
    public void update(T t) {
        this.someDAO.save(t);
    }
    public T getById(Integer id) {
        return this.someDAO.getById(id);
    }

    //list
    public Collection<T> getByCategory(Page page) {
        return this.someDAO.getByCategory(page);
    }

    //category========================================================
    public void initCategoryTree(String rootCategoryName) {
        this.someDAO.initCategoryTree(rootCategoryName);
    }

    public Category getRootCategory() {
        return this.someDAO.getRootCategory();
    }

    public Collection<Category> getCategoryLeaves() {
        return this.someDAO.getCategoryLeaves();
    }
    //category========================================================

    //getter and setter
//    @Autowired
    protected SomeDAO someDAO;
    public abstract void setSomeDAO(SomeDAO someDAO);
}
