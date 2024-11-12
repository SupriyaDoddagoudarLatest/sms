package assignment.sms.domain;

public record Timeslot(
        Long id,
        String start,
        String end,
        String days
) {}