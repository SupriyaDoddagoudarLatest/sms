package assignment.sms.dataloader;

import assignment.sms.entity.Department;
import assignment.sms.repository.DepartmentRepository;
import assignment.sms.service.DepartmentService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsDataLoader;
import org.dataloader.BatchLoader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@DgsDataLoader(name="departmentLoader")
public class DepartmentDataLoader implements BatchLoader<String, Department> {

    @Autowired
    private DepartmentService departmentService;

    @Override
    public CompletableFuture<List<Department>> load(List<String> ids){
        return CompletableFuture.supplyAsync(
                ()-> departmentService.getDepartmentsByIds(ids)

        );
    }


}
