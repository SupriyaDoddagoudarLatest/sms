package assignment.sms.dataloader;

import assignment.sms.entity.Grade;
import assignment.sms.entity.Student;
import assignment.sms.repository.StudentRepository;
import assignment.sms.service.StudentService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsDataLoader;
import org.dataloader.BatchLoaderEnvironment;
import org.dataloader.BatchLoaderWithContext;
import org.dataloader.BatchLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@DgsComponent
@DgsDataLoader(name="studentDataLoader")
public class StudentDataLoader implements BatchLoader<String, Student> {

    @Autowired
    private StudentService studentService;

    @Override
    public CompletionStage<List<Student>> load(List<String> studentIds) {
        return CompletableFuture.supplyAsync(
                ()->
                        studentService.getStudentsById(studentIds)

        );
    }


}

/*@DgsDataLoader(name = "studentDataLoader")
@Component
public class StudentDataLoader implements BatchLoaderWithContext<String, Student> {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public CompletionStage<List<Student>> load(List<String> studentIds, BatchLoaderEnvironment environment) {
        return CompletableFuture.supplyAsync(
                ()->studentRepository.findAllById(studentIds)

                );
    }

}*/
