package assignment.sms.repository;

import assignment.sms.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, String> {
}
