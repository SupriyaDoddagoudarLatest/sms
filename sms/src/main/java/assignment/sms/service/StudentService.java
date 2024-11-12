package assignment.sms.service;

import assignment.sms.domain.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentService {
    private final Map<Long, Student> students = Map.of(
            1L, new Student(1L, "Supriya", List.of(101L, 102L), List.of(201L), 11L, 3.8f),
            2L, new Student(2L, "Suparna", List.of(103L), List.of(202L), 12L, 3.5f)
    );

    public Optional<Student> getStudentById(Long id) {
        return Optional.ofNullable(students.get(id));
    }

    public List<Student> getAllStudents() {
        return List.copyOf(students.values());
    }

    public Student enrollStudentInCourse(Long studentId, Long courseId) {
        Student student = students.get(studentId);
        if (student != null) {
            student.enrolledCourseIds().add(courseId);
        }
        return student;
    }
}
