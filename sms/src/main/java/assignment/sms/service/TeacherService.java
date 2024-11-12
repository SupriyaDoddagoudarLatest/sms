package assignment.sms.service;

import assignment.sms.domain.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TeacherService {
    private final Map<Long, Teacher> teachers = Map.of(
            11L, new Teacher(11L, "Anthony sir", List.of(101L, 103L), 21L),
            12L, new Teacher(12L, "Lavanya mam", List.of(102L), 22L)
    );

    public Optional<Teacher> getTeacherById(Long id) {
        return Optional.ofNullable(teachers.get(id));
    }

    public List<Teacher> getAllTeachers() {
        return List.copyOf(teachers.values());
    }
}


