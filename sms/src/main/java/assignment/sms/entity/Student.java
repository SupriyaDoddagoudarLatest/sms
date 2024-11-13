package assignment.sms.entity;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Student {

    @Id
    private String id;

    private String name;

    @ManyToMany
    private List<Course> enrolledCourses;

    @OneToMany
    private List<Grade> grades;

    @ManyToOne
    private Teacher advisor;

    private Float GPA;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getGPA() {
        return GPA;
    }

    public void setGPA(Float GPA) {
        this.GPA = GPA;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }
}
