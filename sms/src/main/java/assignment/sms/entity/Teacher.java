package assignment.sms.entity;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Teacher {

    @Id
    private String id;

    private String name;

    @ManyToMany(mappedBy = "teachers", fetch = FetchType.EAGER)
    private List<Course> teachingCourses;

    @ManyToOne
    @JoinColumn(name="department_id", referencedColumnName="id")
    private Department department;

    public String getId() {
        return id;
    }

    public List<String> getTeachingCourseIds() {
        if(teachingCourses == null) {
            return Collections.emptyList();
        }
        return teachingCourses.stream().map(Course::getId).collect(Collectors.toList());
    }

    public String getDepartmentId() {
        return department !=null? department.getId() : null;
    }

    public List<Course> getTeachingCourses() {
        return teachingCourses;
    }

    public void setTeachingCourses(List<Course> teachingCourses) {
        this.teachingCourses = teachingCourses;
    }
}
