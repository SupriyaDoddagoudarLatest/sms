package assignment.sms.service;

import assignment.sms.datafetcher.CourseFilter;
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


    public List<Course> getAllCourses(CourseFilter filter){
        String teacherId=filter!=null ? filter.getTeacherId() : null;
        String departmentId = filter != null? filter.getDepartmentId() : null;
        return courseRepository.findCoursesByFilter(teacherId,departmentId);
    }


    @Transactional
    public List<Course> getCoursesByIds(List<String> courseIds) {
        return courseRepository.findAllWithStudentsByIds(courseIds);
    }
}
