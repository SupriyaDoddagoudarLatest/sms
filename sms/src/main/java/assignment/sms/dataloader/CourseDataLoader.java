package assignment.sms.dataloader;

import assignment.sms.entity.Course;
import assignment.sms.entity.Grade;
import assignment.sms.repository.CourseRepository;
import assignment.sms.service.CourseService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsDataLoader;
import org.dataloader.BatchLoader;
import org.dataloader.BatchLoaderEnvironment;
import org.dataloader.BatchLoaderWithContext;
import org.dataloader.MappedBatchLoader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@DgsComponent
@DgsDataLoader(name="courseDataLoader")
public class CourseDataLoader implements BatchLoader<String,Course> {

    @Autowired
    private CourseService courseService;

    @Override
    public CompletableFuture<List<Course>> load(List<String> courseIds) {


        return CompletableFuture.supplyAsync(
                ()->courseService.getCoursesByIds(courseIds));


    }

   /* @Override
    public CompletionStage<Map<String, Course>> load(Set<String> set) {
        return null;
    }*/
}
