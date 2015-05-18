package edu.hust.itec.dao;

import edu.hust.itec.model.News;
import edu.hust.itec.model.User;

/**
 * Created by xsh on 2015/5/15.
 */
public interface UserDAO extends CrudDAO<User> {
    User get(User user);
}
