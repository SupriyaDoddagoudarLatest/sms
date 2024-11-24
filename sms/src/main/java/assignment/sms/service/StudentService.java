package assignment.sms.service;

import assignment.sms.dataloader.GradeDataLoader;
import assignment.sms.entity.Course;
import assignment.sms.entity.Grade;
import assignment.sms.entity.Student;
import assignment.sms.repository.CourseRepository;
import assignment.sms.repository.GradeRepository;
import assignment.sms.repository.StudentRepository;
import org.dataloader.DataLoader;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.concurrent.CompletionStage;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private GradeDataLoader gradeDataLoader;

    /*@Transactional
    public Student studentById(String id) {
        return studentRepository.findById(id).orElse(null);
    }*/

    @Transactional
    public Student getStudentById(String id) {
        Student student = studentRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Student with id " + id + " not found")
        );
        Hibernate.initialize(student.getEnrolledCourseIds());
        Hibernate.initialize(student.getGradeIds());
        return student;
        //  return studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student not found"));
    }

    /*
    public List<Student> getStudentsByIds(List<String> studentIds, String filterCriteria){
        *//*if("active".equals(filterCriteria)){
            return studentRepository.findActiveStudentByIds(studentIds);
        }*//*
        return studentRepository.findAllById(studentIds);
    }
    */


    @Transactional
    public List<Student> allStudents() {
        return studentRepository.findAll();
    }
/*
    @Transactional
    public CompletionStage<Student>  enrollStudentInCourse(String studentId, String courseId, DataLoader<String,Student> studentDataLoader, DataLoader<String,Course> courseDataLoader) {
        return studentDataLoader.load(studentId)
                .thenCombine(courseDataLoader.load(courseId),(student,course)-> {

                    if (student != null && course != null) {
                    course.getStudents().add(student);
                    student.getEnrolledCourses().add(course);


                        return studentRepository.save(student);
                }
                throw new IllegalArgumentException("Student id or course id is null");

                })
                .handle((result,ex)-> {
                 if(ex!=null){
                     throw new RuntimeException("Student id or course id is null"+ex.getMessage());
                 }
                 return result;
                });
    }*/

    @Transactional
    public CompletionStage<Student>  enrollStudentInCourse(String studentId, String courseId, DataLoader<String,Student> studentDataLoader, DataLoader<String,Course> courseDataLoader) {
        return studentDataLoader.load(studentId)
                .thenCombine(courseDataLoader.load(courseId),(student,course)-> {

                    /*Hibernate.initialize(student.getEnrolledCourseIds());
                    Hibernate.initialize(student.getGradeIds());*/

                    if (student != null && course != null) {
                        course.getStudents().add(student);
                        student.getEnrolledCourses().add(course);


                        return studentRepository.save(student);
                    }
                    throw new IllegalArgumentException("Student id or course id is null");

                });
    }

    @Transactional
    public CompletionStage<Student> updateGPA(String studentId, DataLoader<String,List<Grade>> gradeDataLoader) {
        return gradeDataLoader.load(studentId).thenApply(
                grades -> {
                    Student student = studentRepository.findById(studentId).orElse(null);
                    if (student != null) {

                        float totalGrades = (float) grades.stream().mapToDouble(Grade::getScore).sum();
                        float GPA=totalGrades/(grades.size()*10);
                        student.setGPA(GPA);

                        return studentRepository.save(student);
                    }
                    return student;
                });
    }


    public List<Student> getStudentsById(List<String> studentIds) {
        return studentRepository.findAllById(studentIds);
    }
}
