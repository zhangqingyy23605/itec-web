package edu.hust.itec.dao.impl;

import edu.hust.itec.dao.UserDAO;
import edu.hust.itec.model.Category;
import edu.hust.itec.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends CrudDAOImpl<User> implements UserDAO {

    public boolean validate(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("select u from User u " +
                "where u.username=:username and u.password=:password");
        query.setString("username", user.getUsername());
        query.setString("password", user.getPassword());
        User userResult = (User)query.uniqueResult();
        if (userResult != null) {
            return true;
        } else {
            return false;
        }
    }

}
