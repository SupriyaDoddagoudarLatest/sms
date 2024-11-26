package assignment.sms.repository;

import assignment.sms.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, String> {

    @Query("SELECT s FROM Schedule s WHERE s.course.id IN :courseIds")
    List<Schedule> findByCourseIds(@Param("courseIds") List<String> courseIds);

}