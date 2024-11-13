package assignment.sms.datafetcher;

import assignment.sms.entity.*;
import assignment.sms.repository.CourseRepository;
import assignment.sms.repository.GradeRepository;
import assignment.sms.repository.StudentRepository;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
public class MutationFetcher {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private GradeRepository gradeRepository;

    //Enrolls a student in a course.
    @DgsMutation
    public Student enrollStudentInCourse(String studentId, String courseId) {
        // Find the student and course
        Student student = studentRepository.findById(studentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);

        if (student != null && course != null) {
            // Add the student to the course's enrolled students
            course.getStudents().add(student);
            student.getEnrolledCourses().add(course);


            courseRepository.save(course);
            studentRepository.save(student);
        }

        return student;
    }

    //Assigns a grade to a student in a course.
    @DgsMutation
    public Grade assignGradeToStudent(String courseId, String studentId, Float score) {
        // Find the course and student
        Course course = courseRepository.findById(courseId).orElse(null);
        Student student = studentRepository.findById(studentId).orElse(null);

        if (course != null && student != null) {
            // Create a new grade and set the details
            Grade grade = new Grade();
            grade.setCourse(course);
            grade.setStudent(student);
            grade.setScore(score);


            gradeRepository.save(grade);


            return grade;
        }

        return null;  // Return null if course or student was not found
    }
/*

    //Schedules a course with a set of time slots.
    @DgsMutation
    public Course scheduleCourse(String courseId, ScheduleInput scheduleInput) {
        // Find the course and schedule
        Course course = courseRepository.findById(courseId).orElse(null);

        if (course != null) {
            // Create the schedule with the provided time slot ID
            Schedule schedule = new Schedule();
            schedule.setCourse(course);
            // Fetch the time slots from the input (you may need to adapt the time slots logic)
            List<Timeslot> timeSlots = timeslotRepository.findAllById(scheduleInput.getTimeSlotIDs());
            schedule.setTimeSlots(timeSlots);

            // Save the schedule in the database
            scheduleRepository.save(schedule);

            // Attach the schedule to the course
            course.setSchedule(schedule);
            courseRepository.save(course);

            return course;
        }

        return null;
    }
*/

    //Updates the GPA of a student based on their grades.
    @DgsMutation
    public Student updateGPA(String studentId) {
        // Find the student
        Student student = studentRepository.findById(studentId).orElse(null);

        if (student != null) {
            // Calculate GPA based on grades
            float totalGrades = 0;
            int gradeCount = 0;

            for (Grade grade : student.getGrades()) {
                totalGrades += grade.getScore();
                gradeCount++;
            }

            if (gradeCount > 0) {
                float GPA = totalGrades / gradeCount;
                student.setGPA(GPA);


                studentRepository.save(student);
            }
        }

        return student;
    }
}
