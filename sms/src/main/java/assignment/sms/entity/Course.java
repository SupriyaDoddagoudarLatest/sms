package assignment.sms.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Course {

    @Id
    private String id;

    private String name;

    @ManyToMany(mappedBy = "enrolledCourses")
    private List<Student> students;

    @ManyToMany
    private List<Teacher> teachers;

    @OneToMany
    private List<Grade> grades;

    @OneToOne
    private Schedule schedule;

    @ManyToOne
    private Cirriculum cirriculum;

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
