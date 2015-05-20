package edu.hust.itec.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * Created by xsh on 2015/5/18.
 */
@Entity
@DiscriminatorValue("STUDENT")
public class Student extends User {

    public Student() {}
    public Student(User user) {
        super(user);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher mentor;

    public Teacher getMentor() {
        return mentor;
    }

    public void setMentor(Teacher mentor) {
        this.mentor = mentor;
    }
}
