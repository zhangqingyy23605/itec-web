package edu.hust.itec.model;

import org.hibernate.usertype.UserType;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by xsh on 2015/5/18.
 */
@Entity
@DiscriminatorValue("TEACHER")
public class Teacher extends User {
    //TODO 个人主页，个人简介，所属实验室，教授课程，研究方向

    String website;
    String researchGroup;
    String courses;
    String researchAreas;

    public Teacher() {

    }
    public Teacher (User user) {
        super(user);
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getResearchGroup() {
        return researchGroup;
    }

    public void setResearchGroup(String researchGroup) {
        this.researchGroup = researchGroup;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public String getResearchAreas() {
        return researchAreas;
    }

    public void setResearchAreas(String researchAreas) {
        this.researchAreas = researchAreas;
    }
}
