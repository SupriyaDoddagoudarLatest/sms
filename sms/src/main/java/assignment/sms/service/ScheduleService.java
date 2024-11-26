package assignment.sms.service;

import assignment.sms.datafetcher.ScheduleInput;
import assignment.sms.entity.Course;
import assignment.sms.entity.Schedule;
import assignment.sms.entity.Timeslot;
import assignment.sms.repository.CourseRepository;
import assignment.sms.repository.ScheduleRepository;
import assignment.sms.repository.TimeslotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TimeslotRepository timeslotRepository;


    @Transactional
    public CompletableFuture<Schedule> scheduleCourse(String courseId, ScheduleInput scheduleInput) {
        return CompletableFuture.supplyAsync(() -> {
            // Find the course
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new RuntimeException("Course not found for ID: " + courseId));

            // Find the time slot
            Timeslot timeSlot = timeslotRepository.findById(scheduleInput.getTimeslotId())
                    .orElseThrow(() -> new RuntimeException("TimeSlot not found for ID: " + scheduleInput.getTimeslotId()));

            // Find or create the schedule
            Schedule schedule = scheduleRepository.findById(scheduleInput.getScheduleId())
                    .orElse(new Schedule());
            schedule.setCourse(course);
            schedule.setTimeSlots(Collections.singletonList(timeSlot));

            // Save the schedule
            return scheduleRepository.save(schedule);
        });
    }

    public List<Schedule> getSchedulesByCourseIds(List<String> courseIds){
        return scheduleRepository.findByCourseIds(courseIds);
    }
}
