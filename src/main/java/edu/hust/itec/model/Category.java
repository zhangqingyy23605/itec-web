package edu.hust.itec.model;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(indexes = {
        @Index(columnList = "name"),
        @Index(columnList = "parentId")
})
public class Category {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    @NotEmpty
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private Category parent;

//    @OneToMany(mappedBy = "category")
//    private List<News> newsList;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private List<Category> children;

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

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Integer> getLeavesId() {
        return leavesId;
    }

    public void addLeafId(int leafId) {
        this.leavesId.add(leafId);
    }
}
