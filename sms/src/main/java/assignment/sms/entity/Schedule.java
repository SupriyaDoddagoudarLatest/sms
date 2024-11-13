package assignment.sms.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Schedule {

    @Id
    private String id;

    @ManyToOne
    private Course course;

    @OneToMany
    private List<Timeslot> timeSlots;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}