package assignment.sms.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Department {

    @Id
    private String id;

    private String name;

    @ManyToOne
    private Teacher head;

    @OneToMany
    private List<Teacher> teachers;

    @OneToMany
    private List<Course> courses;

    // Getters and Setters
}