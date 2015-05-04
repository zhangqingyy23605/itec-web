package edu.hust.itec.model;

import java.util.*;
import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class NewsCategory {
    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column(unique = true)
    private String name;//热点新闻：生动态、学术交流、项目进展；通知公告：招生公告、招聘通知；

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private NewsCategory parent;

//    @OneToMany(mappedBy = "category")
//    private List<News> newsList;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private List<NewsCategory> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NewsCategory> getChildren() {
        return children;
    }

    public void setChildren(List<NewsCategory> children) {
        this.children = children;
    }

    public NewsCategory getParent() {
        return parent;
    }

    public void setParent(NewsCategory parent) {
        this.parent = parent;
    }
}
