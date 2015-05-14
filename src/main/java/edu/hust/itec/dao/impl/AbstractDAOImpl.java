package edu.hust.itec.dao.impl;

import edu.hust.itec.dao.BaseDAO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.*;

public abstract class AbstractDAOImpl<T, ID extends Serializable> implements BaseDAO<T, ID> {

    private Class<T> clazz;
    @SuppressWarnings("unchecked")
    public AbstractDAOImpl() {
        this.clazz = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    //item
    public boolean saveOrUpdate(T t) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(t);
        return true;
    }
    public boolean deleteById(ID id) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete" + clazz.getName() + "t where t.id = :id");
        query.setParameter("id", id);
        if (query.executeUpdate() == 0 ) {
            return false;
        } else {
            return true;
        }
    }
    @SuppressWarnings("unchecked")
    public T getById(ID id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (T)session.get(clazz, id);
    }

    //list
    @SuppressWarnings("unchecked")
    public Collection<T> getByCategory(
            Collection<Integer> categoryIds, int firstResult, int pageSize) {
        return this.getByCategory(categoryIds, firstResult, pageSize, "", "");
    }

    public Collection<T> getByCategory(
            Collection<Integer> categoryIds, int firstResult, int pageSize,
            String fields) {
        return this.getByCategory(categoryIds, firstResult, pageSize, fields, "");
    }

    @SuppressWarnings("unchecked")
    public Collection<T> getByCategory(
            Collection<Integer> categoryIds, int firstResult, int pageSize,
            String fields, String order) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery(
                "select " + fields + " from " + clazz.getName() +
                        " t where t.category.id in :categoryIds " + order);
        query.setParameterList("categoryIds", categoryIds);
        query.setFirstResult(firstResult);
        query.setMaxResults(pageSize);
        return (Collection<T>)query.list();
    }
    public int getTotalNumberByCategory(Collection<Integer> categoryIds) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from " + clazz.getName() + " t where t.category.id in :categoryIds");
        query.setParameterList("categoryIds", categoryIds);
        return ((Long)query.uniqueResult()).intValue();
    }

    @Autowired
    protected SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
