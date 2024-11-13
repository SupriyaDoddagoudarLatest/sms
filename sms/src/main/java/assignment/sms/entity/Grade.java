package assignment.sms.entity;

import jakarta.persistence.*;

@Entity
public class Grade {

    @Id
    private String id;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Student student;

    private Float score;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
}