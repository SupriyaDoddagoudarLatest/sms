package assignment.sms.domain;

import java.util.List;

public record Curriculum(
        Long id,
        List<Long> courseIds,
        String requirements
) {}