package edu.hust.itec.model;


import org.hibernate.validator.constraints.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(indexes = {
        @Index(columnList = "createTime"),
        @Index(columnList = "categoryId")
})
public class News {
    @Id
    @GeneratedValue
    private Integer id;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

    @NotBlank
    private String heading;

    @Column(columnDefinition = "DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(columnDefinition = "MEDIUMTEXT")
    @NotBlank
    private String content;

//    @Column(name = "editor_id")
    @NotBlank
    private String editor;

//    private int numberOfVisit;

    public News() {
    }

    public News(int id, String heading, Date createTime) {
        this.id = id;
        this.heading = heading;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
