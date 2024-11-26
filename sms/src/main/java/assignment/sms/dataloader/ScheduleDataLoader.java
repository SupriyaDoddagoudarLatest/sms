package assignment.sms.dataloader;

import assignment.sms.entity.Schedule;
import assignment.sms.repository.ScheduleRepository;
import assignment.sms.service.ScheduleService;
import com.netflix.graphql.dgs.DgsDataLoader;
import org.dataloader.BatchLoader;
import org.dataloader.BatchLoaderEnvironment;
import org.dataloader.BatchLoaderWithContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@DgsDataLoader(name="scheduleLoader")
public class ScheduleDataLoader implements BatchLoader<String, Schedule> {

    @Autowired
    private ScheduleService scheduleService;

    @Override
    public CompletionStage<List<Schedule>> load(List<String> courseIds) {
        return CompletableFuture.supplyAsync(() ->
                scheduleService.getSchedulesByCourseIds(courseIds)
        );
    }
}
