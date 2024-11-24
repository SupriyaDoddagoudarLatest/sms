package assignment.sms.repository;

import assignment.sms.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, String> {

    List<Grade> findByStudentId(String studentId);
}
