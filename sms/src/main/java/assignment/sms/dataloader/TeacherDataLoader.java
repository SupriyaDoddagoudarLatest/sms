package assignment.sms.dataloader;

import assignment.sms.entity.Teacher;
import assignment.sms.repository.GradeRepository;
import assignment.sms.repository.TeacherRepository;
import assignment.sms.service.TeacherService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsDataLoader;
import org.dataloader.BatchLoader;
import org.dataloader.BatchLoaderWithContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@DgsDataLoader(name="teacherLoader")
public class TeacherDataLoader implements BatchLoader<String,Teacher> {

    @Autowired
    private TeacherService teacherService;


    @Override
    public CompletableFuture<List<Teacher>> load(List<String> ids){
        return CompletableFuture.supplyAsync(
                ()->teacherService.getTeachersByIds(ids)

        );
    }
}
