package assignment.sms.entity;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Student {

    @Id
    private String id;

    private String name;

    @ManyToMany//(fetch=FetchType.EAGER)
    @JoinTable(
            name = "student_enrolled_courses",
            joinColumns = @JoinColumn(name = "students_id"),
            inverseJoinColumns = @JoinColumn(name = "enrolled_courses_id"))
    private List<Course> enrolledCourses;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch=FetchType.LAZY)//(fetch=FetchType.EAGER)
    private List<Grade> grades;

    @ManyToOne
    private Teacher advisor;

    private Float GPA;

   // private boolean active;

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


    public List<String> getEnrolledCourseIds() {
        if(enrolledCourses == null){
            return Collections.emptyList();
        }
        return enrolledCourses.stream().map(Course::getId).collect(Collectors.toList());
    }
    public List<String> getGradeIds() {
        if(grades == null){
            return Collections.emptyList();
        }
        return grades.stream().map(Grade::getId).collect(Collectors.toList());
    }

    public String getAdvisorId() {
        return advisor != null ? advisor.getId() : null;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
