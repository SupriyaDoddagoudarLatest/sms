package assignment.sms.entity;

import jakarta.persistence.*;

@Entity
public class Timeslot {

    @Id
    private String id;

    private String start;

    private String end;

    private String days;

}
