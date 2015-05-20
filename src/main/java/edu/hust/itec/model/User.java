package edu.hust.itec.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.type.EnumType;
import org.springframework.util.ReflectionUtils;

/**
 * Created by xsh on 2015/5/15.
 */
@Entity
@Table(indexes = {
        @Index(columnList = "email"),
        @Index(columnList = "privilege")
})
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String fullname;

    @Email
    @NotNull
    private String email;

    @NotNull
    private Privilege privilege;

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    @NotNull(message = "类别不能为空")
    private Category category;

    public User() {
    }
    public User(User user) {
        ReflectionUtils.shallowCopyFieldState(user, this);
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public enum Privilege {
        ROOT, EDITOR, SELF, FORBIDDEN
    /*
    0: 最高权限
    1: 能添加新闻并修改自己添加的新闻
    2: 普通用户，登陆只能填写个人信息
    3: 禁止登陆（需要管理员通过）
    */
    }
}
