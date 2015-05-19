package edu.hust.itec.service;

import edu.hust.itec.model.Teacher;
import edu.hust.itec.model.User;

import java.util.Collection;

/**
 * Created by xsh on 2015/5/13.
 */

public interface UserService extends CrudService<User> {

    User getByExample(User user);

    Collection<Teacher> getTeachers();
}