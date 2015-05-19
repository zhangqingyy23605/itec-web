package edu.hust.itec.model;

import javax.persistence.*;

/**
 * Created by xsh on 2015/5/18.
 */
@Entity
@DiscriminatorValue("TEACHER")
public class Teacher extends User {
    //TODO 个人主页，个人简介，所属实验室，教授课程，研究方向

    String website;
    String introduction;
    String researchGroup;
    String researchAreas;
    String teachingCourses;

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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getResearchGroup() {
        return researchGroup;
    }

    public void setResearchGroup(String researchGroup) {
        this.researchGroup = researchGroup;
    }

    public String getTeachingCourses() {
        return teachingCourses;
    }

    public void setTeachingCourses(String teachingCourses) {
        this.teachingCourses = teachingCourses;
    }

    public String getResearchAreas() {
        return researchAreas;
    }

    public void setResearchAreas(String researchAreas) {
        this.researchAreas = researchAreas;
    }
}
