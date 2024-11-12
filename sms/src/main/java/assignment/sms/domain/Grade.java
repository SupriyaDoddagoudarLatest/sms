package assignment.sms.domain;

public record Grade(
        Long id,
        Long courseId,
        Long studentId,
        Float score
) {}
