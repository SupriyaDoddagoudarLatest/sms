package assignment.sms.domain;

import java.util.List;

public record Teacher(
        Long id,
        String name,
        List<Long> teachingCourseIds,
        Long departmentId
) {}
