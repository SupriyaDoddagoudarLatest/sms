package assignment.sms.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cirriculum {

    @Id
    private String id;

    @ManyToMany
    private List<Course> courses;

    private String requirements;

}
