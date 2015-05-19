package edu.hust.itec.dao;

import edu.hust.itec.model.News;
import edu.hust.itec.model.Teacher;
import edu.hust.itec.model.User;

import java.util.Collection;

/**
 * Created by xsh on 2015/5/15.
 */
public interface UserDAO extends CrudDAO<User> {
    User getByExample(User user);

    Collection<Teacher> getTeachers();
}
