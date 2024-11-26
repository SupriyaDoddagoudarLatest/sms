/*
package assignment.sms.datafetcher;

import assignment.sms.entity.*;
import assignment.sms.service.CourseService;
import assignment.sms.service.StudentService;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Configuration
public class DataLoaderConfig {


    private final StudentService studentService;
    private final CourseService courseService;
    //private final TeacherService teacherService;

    public DataLoaderConfig(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }


    @Bean
    public DataLoaderRegistry dataLoaderRegistry() {
        DataLoaderRegistry registry = new DataLoaderRegistry();


        registry.register("courseDataLoader",DataLoader.newMappedDataLoader(courseIds->
                CompletableFuture.supplyAsync(()-> courseService.getCoursesByIds(toList(courseIds)))
                        .thenApply(courses -> courses.stream()
                                .collect(Collectors.toMap(Course::getId, course->course)))

                ));


        registry.register("studentDataLoader", DataLoader.newMappedDataLoader(studentIds->
                        CompletableFuture.supplyAsync(()->studentService.getStudentsByIds(toList(studentIds)))
                                .thenApply(students->students.stream().collect(Collectors.toMap(Student::getId, student->student)))
                ));

        return registry;

    }

    private ArrayList<String> toList(Set<Object> Ids) {
        return (ArrayList<String>) Ids.stream().map(Object::toString).collect(Collectors.toList());
    }




}
*/
