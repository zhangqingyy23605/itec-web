package edu.hust.itec.service.impl;

import edu.hust.itec.dao.UserDAO;
import edu.hust.itec.model.Teacher;
import edu.hust.itec.model.User;
import edu.hust.itec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class UserServiceImpl extends CrudServiceImpl<User, UserDAO> implements UserService {

    public User getByExample(User user) {
        return this.someDAO.getByExample(user);
    }

    public Collection<Teacher> getTeachers() {
        return this.someDAO.getTeachers();
    }

    @Autowired
    public void setSomeDAO(UserDAO someDAO) {
        this.someDAO = someDAO;
    }
}
