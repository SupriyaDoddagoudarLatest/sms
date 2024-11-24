package assignment.sms.service;

import assignment.sms.entity.Teacher;
import assignment.sms.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> getTeachersByIds(List<String> ids) {
        return teacherRepository.findAllById(ids);
    }

}
