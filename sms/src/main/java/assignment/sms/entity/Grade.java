package assignment.sms.entity;

import assignment.sms.repository.GradeRepository;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Entity
public class Grade {
    @Id
    /*@GenericGenerator(
            name = "Incremental",
            strategy = "org.hibernate.id.IncrementGenerator"
    )*/
    private String id;

    @ManyToOne
    private Course course;

    @ManyToOne
    @JoinColumn(name="student_id", referencedColumnName = "id")
    private Student student;

    private Float score;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }


    @PrePersist
    public void generateId(){
        if(this.id==null){
            this.id= UUID.randomUUID().toString();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}