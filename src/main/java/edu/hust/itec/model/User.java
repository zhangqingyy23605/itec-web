package edu.hust.itec.model;

import javax.persistence.*;

/**
 * Created by xsh on 2015/5/15.
 */
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String password;
    private String fullname;

    //0: 最高权限, 1: 能添加新闻并修改自己添加的新闻, 2: 普通用户，登陆只能填写个人信息
    private Integer privilege = 2;

    //教授、副教授、讲师、本科生、研究生、博士生
    private UserType type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Integer privilege) {
        this.privilege = privilege;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}
