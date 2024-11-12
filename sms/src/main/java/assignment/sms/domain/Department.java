package assignment.sms.domain;

import java.util.List;

public record Department(
        Long id,
        String name,
        Long headId,
        List<Long> teacherIds,
        List<Long> courseIds
) {}