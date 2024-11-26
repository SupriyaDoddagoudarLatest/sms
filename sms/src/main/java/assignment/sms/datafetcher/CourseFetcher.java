package assignment.sms.datafetcher;

import assignment.sms.dataloader.CourseDataLoader;

import assignment.sms.dataloader.TimeslotDataLoader;
import assignment.sms.entity.Course;
import assignment.sms.entity.Student;
import assignment.sms.entity.Timeslot;
import assignment.sms.repository.CourseRepository;
import assignment.sms.service.CourseService;
import assignment.sms.service.ScheduleService;
import com.netflix.graphql.dgs.*;
import com.netflix.graphql.dgs.context.DgsContext;
import org.dataloader.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@DgsComponent
public class CourseFetcher {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ScheduleService scheduleService;

    @DgsQuery
    public CompletionStage<List<Course>> allCourses(@InputArgument CourseFilter filter, DgsDataFetchingEnvironment dfe) {

        DataLoader<String, Course> courseDataLoader = dfe.getDataLoader("courseDataLoader");
        List<Course> filteredCourses = courseService.getAllCourses(filter);
        List<String> courseIds = filteredCourses.stream().map(Course::getId).collect(Collectors.toList());
        //return courseService.allCourses();
        return courseDataLoader.loadMany(courseIds);
    }

    @DgsMutation
    public CompletionStage<Course> scheduleCourse(@InputArgument String courseId,@InputArgument ScheduleInput scheduleInput, DgsDataFetchingEnvironment dfe) {
        DataLoader<String, Course> courseDataLoader = dfe.getDataLoader("courseDataLoader");

        return scheduleService.scheduleCourse(courseId, scheduleInput).thenCompose(updatedSchedule -> courseDataLoader.load(courseId));
    }

    @DgsData(parentType = "Course", field="students")
    public CompletionStage<List<Student>> getStudents(DgsDataFetchingEnvironment dfe) {
        Course course = dfe.getSource();
        DataLoader<String, Student> studentDataLoader = dfe.getDataLoader("studentDataLoader");
        return studentDataLoader.loadMany(course.getStudentIds());
    }


}
