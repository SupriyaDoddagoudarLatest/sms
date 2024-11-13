package assignment.sms.datafetcher;

import assignment.sms.entity.Course;
import assignment.sms.repository.CourseRepository;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import org.springframework.beans.factory.annotation.Autowired;

@DgsComponent
public class CourseFetcher {

    @Autowired
    private CourseRepository courseRepository;

    @DgsQuery
    public Iterable<Course> allCourses() {
        return courseRepository.findAll();
    }
}
