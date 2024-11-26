package assignment.sms.dataloader;

import assignment.sms.entity.Grade;
import assignment.sms.repository.GradeRepository;
import assignment.sms.service.GradeService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsDataLoader;
import org.dataloader.BatchLoader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;


@DgsDataLoader(name="gradeDataLoader")
public class GradeDataLoader implements BatchLoader<String, List<Grade>> {


    @Autowired
    private GradeService gradeService;

    @Override
    public CompletionStage<List<List<Grade>>> load(List<String> studentIds) {
        return CompletableFuture.supplyAsync(
                ()->gradeService.getGradesByStudentIds(studentIds)

        );
    }


}

/*@DgsDataLoader(name="gradeDataLoader")
public class GradeDataLoader implements BatchLoader<String, List<Grade>> {

    @Autowired
    private GradeRepository gradeRepository;

    public CompletionStage<List<List<Grade>>> load(List<String> studentIds) {
     return CompletableFuture.supplyAsync(()->
             studentIds.stream().map(gradeRepository::findAllByStudentId).collect(Collectors.toList()));
    }
}*/
