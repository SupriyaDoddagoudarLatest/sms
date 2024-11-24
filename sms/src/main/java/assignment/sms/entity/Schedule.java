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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Timeslot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<Timeslot> timeSlots) {
        this.timeSlots = timeSlots;
    }
}