package edu.hust.itec.dao.impl;

import edu.hust.itec.dao.UserDAO;
import edu.hust.itec.model.Category;
import edu.hust.itec.model.Teacher;
import edu.hust.itec.model.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class UserDAOImpl extends CrudDAOImpl<User> implements UserDAO {

    public User getByExample(User user) {
        Session session = this.sessionFactory.getCurrentSession();

        Example example = Example.create(user).ignoreCase();
        Criteria criteria = session.createCriteria(User.class).add(example);

        User userResult = (User)criteria.uniqueResult();
        return userResult;
    }

    @SuppressWarnings("unchecked")
    public Collection<Teacher> getTeachers() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("select t from Teacher t");
        return (Collection<Teacher>)query.list();
    }
}
