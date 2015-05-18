package edu.hust.itec.service.impl;

import edu.hust.itec.dao.UserDAO;
import edu.hust.itec.model.User;
import edu.hust.itec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl extends CrudServiceImpl<User, UserDAO> implements UserService {

    public User get(User user) {
        user.setUsername(user.getUsername().toLowerCase());
        return this.someDAO.get(user);
    }

    @Autowired
    public void setSomeDAO(UserDAO someDAO) {
        this.someDAO = someDAO;
    }
}
