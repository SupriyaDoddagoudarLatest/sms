package assignment.sms.datafetcher;

import assignment.sms.entity.Student;
import assignment.sms.repository.StudentRepository;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import org.springframework.beans.factory.annotation.Autowired;

@DgsComponent
public class StudentFetcher {

    @Autowired
    private StudentRepository studentRepository;

    @DgsQuery
    public Student studentById(String id) {
        return studentRepository.findById(id).orElse(null);
    }
}