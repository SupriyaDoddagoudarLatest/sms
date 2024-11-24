package assignment.sms.entity;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Course {

    @Id
    private String id;

    private String name;

    @ManyToMany(mappedBy = "enrolledCourses")
    private List<Student> students;

    @ManyToMany//(fetch = FetchType.EAGER)
    @JoinTable(name="course_teachers",
            joinColumns = @JoinColumn(name = "teachers_id"),
            inverseJoinColumns = @JoinColumn(name = "teaching_courses_id"))
    private List<Teacher> teachers;

    @OneToMany//(fetch = FetchType.EAGER)
    private List<Grade> grades;

    @OneToOne
    private Schedule schedule;

    @ManyToOne
    private Cirriculum cirriculum;

    public Schedule getSchedule() {
        return schedule;
    }

   // boolean current ;
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getId() {
        return id;
    }

    public List<String> getStudentIds(){
        if(students == null){
            return Collections.emptyList();
        }
        return students.stream()
                .map(Student::getId)
                .collect(Collectors.toList());
    }
}
