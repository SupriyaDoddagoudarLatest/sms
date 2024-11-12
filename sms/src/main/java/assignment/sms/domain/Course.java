package assignment.sms.domain;

import java.util.List;

public record Course(
        Long id,
        String name,
        List<Long> studentIds,
        List<Long> teacherIds,
        List<Long> gradeIds,
        Long scheduleId,
        Long curriculumId
) {}