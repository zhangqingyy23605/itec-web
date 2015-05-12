package edu.hust.itec.model;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;

@Entity
//@Table(indexes = {
//        @Index(columnList = "name")
//})
public class NewsCategory {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String name;//热点新闻：生动态、学术交流、项目进展；通知公告：招生公告、招聘通知；

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private NewsCategory parent;

//    @OneToMany(mappedBy = "category")
//    private List<News> newsList;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private List<NewsCategory> children;

    @Transient
    private List<Integer> leavesId = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<Integer> getLeavesId() {
        return leavesId;
    }

    public void addLeafId(int leafId) {
        this.leavesId.add(leafId);
    }
}
