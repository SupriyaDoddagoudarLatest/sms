package assignment.sms.repository;

import assignment.sms.entity.Grade;
import assignment.sms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {

    @Query("SELECT s  FROM Student s " +
            "LEFT JOIN FETCH s.enrolledCourses " +
            "WHERE s.id IN :studentIds")
    List<Student> findAllWithEnrolledCoursesByIds(@Param("studentIds") List<String> studentIds);

    @Query("SELECT s  FROM Student s " +
            "LEFT JOIN FETCH s.grades " +
            "WHERE s.id IN :studentIds")
    List<Student> findAllWithGradesByIds(@Param("studentIds") List<String> studentIds);

}
