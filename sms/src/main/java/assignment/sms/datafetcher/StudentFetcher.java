package assignment.sms.datafetcher;
import assignment.sms.dataloader.CourseDataLoader;
import assignment.sms.entity.*;
import assignment.sms.service.GradeService;
import assignment.sms.service.ScheduleService;
import assignment.sms.service.StudentService;
import com.netflix.graphql.dgs.*;
import graphql.schema.DataFetchingEnvironment;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@DgsComponent
public class StudentFetcher {
    @Autowired
    private StudentService studentService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private ScheduleService scheduleService;

    @DgsQuery
    public CompletionStage<Student> studentById(@InputArgument String id, DataFetchingEnvironment dfe) {
        DataLoader<String,Student> studentDataLoader = dfe.getDataLoader("studentDataLoader");
        return studentDataLoader.load(id);
        //return CompletableFuture.supplyAsync(()->studentService.getStudentById(id));
    }

    @DgsMutation
    public CompletionStage<Student> enrollStudentInCourse(String studentId, String courseId, DgsDataFetchingEnvironment dfe) {
        /*DataLoaderRegistry registry = dfe.getDataLoaderRegistry();

        if(registry.getDataLoader("courseDataLoader")==null){
            throw new RuntimeException("Course DataLoader is not registered in DataLoaderRegistry");
        }*/

        DataLoader<String, Student> studentDataLoader = dfe.getDataLoader("studentDataLoader");
        DataLoader<String, Course> courseDataLoader = dfe.getDataLoader("courseDataLoader");
        return studentService.enrollStudentInCourse(studentId, courseId,studentDataLoader, courseDataLoader);
    }


    @DgsMutation
    public CompletionStage<Grade> assignGradeToStudent(String courseId, String studentId, Float score, DataFetchingEnvironment dfe) {
        DataLoader<String, Course> courseDataLoader = dfe.getDataLoader("courseDataLoader");
        DataLoader<String, Student> studentDataLoader = dfe.getDataLoader("studentDataLoader");

        return gradeService.assignGradeToStudent(courseId,studentId,score,courseDataLoader,studentDataLoader);
    }

    @DgsMutation
    public CompletionStage<Student> updateGPA(String studentId, DgsDataFetchingEnvironment dfe) {

        DataLoader<String, List<Grade>> gradeDataLoader = dfe.getDataLoader("gradeDataLoader");
        return studentService.updateGPA(studentId,gradeDataLoader);

    }


    //Resolve enrolledCourses for Student

    @DgsData(parentType = "Student", field = "enrolledCourses")
    public CompletableFuture<List<Course>> getEnrolledCourses(DgsDataFetchingEnvironment env) {
        Student student = env.getSource();
        DataLoader<String, Course> dataLoader = env.getDataLoader("courseDataLoader");
        return dataLoader.loadMany(student.getEnrolledCourseIds());
    }

    //Resolve grades for Student

    @DgsData(parentType = "Student", field = "grades")
    public CompletableFuture<List<Grade>> getGrades(DgsDataFetchingEnvironment env) {
        Student student = env.getSource();
        DataLoader<String, Grade> dataLoader = env.getDataLoader("gradeDataLoader");
        return dataLoader.loadMany(student.getGradeIds());
    }

    //Resolve advisor for Student
    @DgsData(parentType = "Student", field = "advisor")
    public CompletableFuture<Teacher> getAdvisor(DgsDataFetchingEnvironment env) {
        Student student = env.getSource();
        DataLoader<String, Teacher> dataLoader = env.getDataLoader("teacherLoader");
        return dataLoader.load(student.getAdvisorId());
    }

    //Resolve teachingCourses for Teacher
    @DgsData(parentType = "Teacher", field = "teachingCourses")
    public CompletableFuture<List<Course>> getTeachingCourses(DgsDataFetchingEnvironment env) {
        Teacher teacher = env.getSource();
        DataLoader<String, Course> dataLoader = env.getDataLoader("courseDataLoader");
        return dataLoader.loadMany(teacher.getTeachingCourseIds());
    }

   //Resolve department for Teacher
    @DgsData(parentType = "Teacher", field = "department")
    public CompletableFuture<Department> getDepartment(DgsDataFetchingEnvironment env) {
        Teacher teacher = env.getSource();
        DataLoader<String, Department> dataLoader = env.getDataLoader("departmentLoader");
        return dataLoader.load(teacher.getDepartmentId());
    }

    //Resolve schedule for Course
    @DgsData(parentType = "Course", field = "schedule")
    public CompletableFuture<Schedule> getSchedule(DgsDataFetchingEnvironment env) {
        Course course = env.getSource();
        DataLoader<String, Schedule> scheduleDataLoader = env.getDataLoader("scheduleLoader");
        return scheduleDataLoader.load(course.getId());
    }

}