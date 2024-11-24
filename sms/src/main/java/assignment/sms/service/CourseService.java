package assignment.sms.service;

import assignment.sms.datafetcher.ScheduleInput;
import assignment.sms.entity.Course;
import assignment.sms.entity.Schedule;
import assignment.sms.entity.Timeslot;
import assignment.sms.repository.CourseRepository;
import assignment.sms.repository.ScheduleRepository;
import com.netflix.graphql.dgs.DgsQuery;
import jakarta.transaction.Transactional;
import org.dataloader.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletionStage;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    //ScheduleInput scheduleInput = new ScheduleInput();

    public Iterable<Course> allCourses() {
        return courseRepository.findAll();
    }


    @Transactional
    public CompletionStage<Course> scheduleCourse(String courseId, ScheduleInput scheduleInput,
                                                  DataLoader<String, Course> courseDataLoader,
                                                  DataLoader<String, Timeslot> timeslotDataLoader) {

        return courseDataLoader.load(courseId)
                .thenCombine(timeslotDataLoader.load(scheduleInput.getTimeslotId()), (course, timeslot) -> {
                    if (course == null) {
                        throw new IllegalArgumentException("Course not found for ID: " + courseId);
                    }

                    if (timeslot == null) {
                        throw new IllegalArgumentException("Timeslot not found for ID: " + scheduleInput.getTimeslotId());
                    }

                    // Create or update the Schedule
                    Schedule schedule = scheduleRepository.findById(scheduleInput.getScheduleId())
                            .orElse(new Schedule());
                    schedule.setId(scheduleInput.getScheduleId());
                    schedule.setCourse(course);
                    schedule.setTimeSlots(List.of(timeslot));

                    // Save Schedule
                    scheduleRepository.save(schedule);

                    // Attach Schedule to the Course
                    course.setSchedule(schedule);
                    return courseRepository.save(course); // Return the updated course
                });
    }

    @Transactional
    public List<Course> getCoursesByIds(List<String> courseIds) {
        return courseRepository.findAllById(courseIds);
    }
}
