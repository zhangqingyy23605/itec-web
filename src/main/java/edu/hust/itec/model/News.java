package edu.hust.itec.model;


import org.hibernate.validator.constraints.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @JoinColumn(name = "categoryId")
    @NotNull(message = "类别不能为空")
    private Category category;

    @NotBlank(message = "标题不能为空")
    private String heading;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(columnDefinition = "MEDIUMTEXT")
    @NotBlank(message = "正文不能为空")
    private String content;

//    @Column(name = "editor_id")
    @NotBlank(message = "作者不能为空")
    private String editor;
    //TODO news editor 由改为 User类型的 editor_id：做用户管理的时候改进

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
