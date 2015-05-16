package edu.hust.itec.service.impl;

import edu.hust.itec.dao.NewsDAO;
import edu.hust.itec.dao.UserDAO;
import edu.hust.itec.model.News;
import edu.hust.itec.model.User;
import edu.hust.itec.service.NewsService;
import edu.hust.itec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl extends CrudServiceImpl<User, UserDAO> implements UserService {

    public boolean validate(User user) {
        return this.someDAO.validate(user);
    }

    @Autowired
    public void setSomeDAO(UserDAO someDAO) {
        this.someDAO = someDAO;
    }
}
