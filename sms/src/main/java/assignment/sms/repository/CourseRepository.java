package assignment.sms.repository;

import assignment.sms.entity.Course;
import assignment.sms.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {

    @Query("Select c from Course c left join fetch c.students where c.id in :courseIds ")
    List<Course> findAllWithStudentsByIds(@Param("courseIds") List<String> courseIds);


    @Query("Select c from Course c left join c.teachers t left join t.department d where(:teacherId is null or t.id=:teacherId) and (:departmentId is null or d.id = :departmentId)")
    List<Course> findCoursesByFilter(@Param("teacherId") String teacherId, @Param("departmentId") String departmentId);
}
