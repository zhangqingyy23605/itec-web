package edu.hust.itec.dao.impl;

import edu.hust.itec.dao.UserDAO;
import edu.hust.itec.model.Category;
import edu.hust.itec.model.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends CrudDAOImpl<User> implements UserDAO {

    public User get(User user) {
        Session session = this.sessionFactory.getCurrentSession();

        Example example = Example.create(user).ignoreCase();
        Criteria criteria = session.createCriteria(User.class).add(example);

        User userResult = (User)criteria.uniqueResult();
        return userResult;
    }
}
