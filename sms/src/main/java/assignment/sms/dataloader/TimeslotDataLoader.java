package assignment.sms.dataloader;

import assignment.sms.entity.Timeslot;
import assignment.sms.repository.TimeslotRepository;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsDataLoader;
import org.dataloader.BatchLoaderEnvironment;
import org.dataloader.BatchLoaderWithContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


@DgsDataLoader(name="timeslotDataLoader")
public class TimeslotDataLoader implements BatchLoaderWithContext<String, Timeslot> {

    @Autowired
    private TimeslotRepository timeslotRepository;

    @Override
    public CompletionStage<List<Timeslot>> load(List<String> timeslotIds, BatchLoaderEnvironment environment) {
        return CompletableFuture.supplyAsync(() -> timeslotRepository.findAllById(timeslotIds));
    }
}