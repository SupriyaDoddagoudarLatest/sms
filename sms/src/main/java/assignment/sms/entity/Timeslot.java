package assignment.sms.entity;

import jakarta.persistence.*;

@Entity
public class Timeslot {

    @Id
    private String id;

    private String start;

    private String end;

    private String days;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false) // Foreign key to Schedule
    private Schedule schedule;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
