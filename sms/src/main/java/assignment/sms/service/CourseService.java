package assignment.sms.service;

import assignment.sms.domain.Course;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CourseService {
    private final Map<Long, Course> courses = Map.of(
            101L, new Course(101L, "Math 101", List.of(1L), List.of(11L), List.of(201L), null, null),
            102L, new Course(102L, "Science 102", List.of(1L), List.of(12L), List.of(202L), null, null),
            103L, new Course(103L, "History 103", List.of(2L), List.of(11L), List.of(203L), null, null)
    );

    public Optional<Course> getCourseById(Long id) {
        return Optional.ofNullable(courses.get(id));
    }

    public List<Course> getAllCourses() {
        return List.copyOf(courses.values());
    }
}
