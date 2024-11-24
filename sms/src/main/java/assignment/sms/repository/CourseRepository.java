package assignment.sms.repository;

import assignment.sms.entity.Course;
import assignment.sms.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {

}
