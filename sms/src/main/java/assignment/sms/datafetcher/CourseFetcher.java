package assignment.sms.datafetcher;

import assignment.sms.dataloader.CourseDataLoader;
import assignment.sms.dataloader.StudentDataLoader;
import assignment.sms.dataloader.TimeslotDataLoader;
import assignment.sms.entity.Course;
import assignment.sms.entity.Student;
import assignment.sms.entity.Timeslot;
import assignment.sms.repository.CourseRepository;
import assignment.sms.service.CourseService;
import com.netflix.graphql.dgs.*;
import org.dataloader.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CompletionStage;

@DgsComponent
public class CourseFetcher {

    @Autowired
    private CourseService courseService;

    @DgsQuery
    public Iterable<Course> allCourses() {

        return courseService.allCourses();
    }

    @DgsMutation
    public CompletionStage<Course> scheduleCourse(String courseId, ScheduleInput scheduleInput, DgsDataFetchingEnvironment dfe) {
        DataLoader<String, Course> courseDataLoader = dfe.getDataLoader(CourseDataLoader.class);
        DataLoader<String, Timeslot> timeslotDataLoader = dfe.getDataLoader(TimeslotDataLoader.class);

        return courseService.scheduleCourse(courseId, scheduleInput, courseDataLoader, timeslotDataLoader);
    }

    @DgsData(parentType = "Course", field="students")
    public CompletionStage<List<Student>> getStudents(DgsDataFetchingEnvironment dfe) {
        Course course = dfe.getSource();
        DataLoader<String, Student> studentDataLoader = dfe.getDataLoader("studentDataLoader");
        return studentDataLoader.loadMany(course.getStudentIds());
    }


}
