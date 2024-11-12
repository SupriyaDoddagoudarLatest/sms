package assignment.sms.domain;

import java.util.List;

public record Student(
        Long id,
        String name,
        List<Long> enrolledCourseIds,
        List<Long> gradeIds,
        Long advisorId,
        Float gpa
) {}
