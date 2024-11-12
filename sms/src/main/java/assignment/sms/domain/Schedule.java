package assignment.sms.domain;

import java.util.List;

public record Schedule(
        Long id,
        Long courseId,
        List<Long> timeSlotIds
) {}