package assignment.sms.datafetcher;

import assignment.sms.domain.Student;
import assignment.sms.service.StudentService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.dataloader.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CompletableFuture;

@DgsComponent
public class StudentDataFetcher {

    @Autowired
    private StudentService studentService;

    @DgsQuery
    public CompletableFuture<Student> studentById(@InputArgument Long id, DgsDataFetchingEnvironment dfe) {
        DataLoader<Long, Student> studentLoader = dfe.getDataLoader("studentLoader");
        return studentLoader.load(id);
    }
}
