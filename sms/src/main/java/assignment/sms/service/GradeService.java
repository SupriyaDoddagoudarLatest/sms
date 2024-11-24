package assignment.sms.service;

import assignment.sms.entity.Course;
import assignment.sms.entity.Grade;
import assignment.sms.entity.Student;
import assignment.sms.repository.CourseRepository;
import assignment.sms.repository.GradeRepository;
import assignment.sms.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.dataloader.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;


    @Transactional
    public CompletionStage<Grade> assignGradeToStudent(String courseId, String studentId, Float score,
                                                       DataLoader<String, Course> courseDataLoader,
                                                       DataLoader<String, Student> studentDataLoader) {
        return courseDataLoader.load(courseId)
                .thenCombine(studentDataLoader.load(studentId),(course,student)->{

                    if(course!=null && student!=null) {
                        Grade grade = new Grade();

                        grade.setCourse(course);
                        grade.setStudent(student);
                        grade.setScore(score);

                        return gradeRepository.save(grade);

                    }
                    throw new RuntimeException("Course or student is null");
                });




    }

    @Transactional
    public List<List<Grade>> getGradesByStudentIds(List<String> studentIds) {
        return studentIds.stream().map(gradeRepository::findByStudentId).collect(Collectors.toList());
    }
}
