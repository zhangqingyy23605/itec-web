package edu.hust.itec.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(indexes = {
        @Index(columnList = "addDate"),
        @Index(columnList = "category_id")
})
public class News {
    @Id
    @GeneratedValue
    private int id;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private NewsCategory category;

    private String heading;

    @Column(columnDefinition = "DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addDate;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String content;

    private String editor;

//    private int numberOfVisit;

    public News() {
    }

    public News(String heading, Date addDate, int id) {
        this.heading = heading;
        this.addDate = addDate;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NewsCategory getCategory() {
        return category;
    }

    public void setCategory(NewsCategory category) {
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

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }
}
