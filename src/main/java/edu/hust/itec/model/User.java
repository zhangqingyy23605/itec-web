package edu.hust.itec.model;

/**
 * Created by xsh on 2015/5/9.
 */
public class User {
    private Integer id;
    private String username;
    private String password;
    private String fullname;
    private Integer privilege;//0: root, 1: editor只能修改自己添加的, 2: 普通用户，登陆也只能查看
    private String category;//教授、副教授、本科生、研究生



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

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
