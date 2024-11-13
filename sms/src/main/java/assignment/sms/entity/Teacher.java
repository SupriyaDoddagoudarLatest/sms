package assignment.sms.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Teacher {

    @Id
    private String id;

    private String name;

    @ManyToMany(mappedBy = "teachers")
    private List<Course> teachingCourses;

    @ManyToOne
    private Department department;


}
