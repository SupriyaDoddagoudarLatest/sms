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

import java.util.ArrayList;
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
    }

    @Transactional
    public List<Student> allStudents() {
        return studentRepository.findAll();
    }

    @Transactional
    public CompletionStage<Student>  enrollStudentInCourse(String studentId, String courseId, DataLoader<String,Student> studentDataLoader, DataLoader<String,Course> courseDataLoader) {
        return studentDataLoader.load(studentId)
                .thenCombine(courseDataLoader.load(courseId),(student,course)-> {

                    /*Hibernate.initialize(student.getEnrolledCourses());
                    Hibernate.initialize(student.getAdvisor().getTeachingCourses());*/

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


    @Transactional
    public List<Student> getStudentsById(List<String> studentIds) {
        //return studentRepository.findAllById(studentIds);

        if(studentRepository.findAllById(studentIds).size()==0){
            throw new IllegalArgumentException("Student entry with this id is not found in DB");
        }else if (studentRepository.findAllById(studentIds).size()!=studentIds.size()){
            throw new IllegalArgumentException("Mismatch in the id provideed and DB entry");
        }

        List<Student> students=studentRepository.findAllWithEnrolledCoursesByIds(studentIds);
        studentRepository.findAllWithGradesByIds(studentIds);

        return students;
    }
/*
    //For the new Dataloader registry
    public List<Student> getStudentsByIds(ArrayList<String> ids) {
        return studentRepository.findAllById(ids);
    }*/
}
