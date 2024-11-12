package assignment.sms.dataloader;

import assignment.sms.domain.Student;
import assignment.sms.service.StudentService;
import com.netflix.graphql.dgs.DgsDataLoader;
import org.dataloader.MappedBatchLoader;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@DgsDataLoader(name = "studentLoader")
@Component
public class StudentDataLoader implements MappedBatchLoader<Long, Student> {

    private final StudentService studentService;

    public StudentDataLoader(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public CompletionStage<Map<Long, Student>> load(Set<Long> studentIds) {
        return CompletableFuture.supplyAsync(() ->
                studentIds.stream()
                        .map(studentService::getStudentById)
                        .filter(java.util.Optional::isPresent)
                        .map(student -> Map.entry(student.get().id(), student.get()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
        );
    }
}
