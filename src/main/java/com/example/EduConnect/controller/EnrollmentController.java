package com.example.EduConnect.controller;

import com.example.EduConnect.entity.Course;
import com.example.EduConnect.entity.Enrollment;
import com.example.EduConnect.entity.User;
import com.example.EduConnect.service.CourseService;
import com.example.EduConnect.service.EnrollmentService;
import com.example.EduConnect.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;
    private final UserService userService;
    private final CourseService courseService;


    public EnrollmentController(EnrollmentService enrollmentService, UserService userService, CourseService courseService){
        this.enrollmentService = enrollmentService;
        this.userService = userService;
        this.courseService = courseService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public List<Enrollment> getAllEnrollments(){
        return enrollmentService.getAllEnrollment();
    }

    @PostMapping
    @PreAuthorize("hasRole('STUDENT')")
    public void addEnrollment(@RequestParam Long courseId){
        enrollmentService.enrollUserInCourse(courseId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public Enrollment getEnrollmentById(@PathVariable Long id){
        return enrollmentService.getEnrollmentById(id);
    }

    @GetMapping("/my-courses")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    List<Course> getInstructorCourses(){
        User currentUser = userService.getCurrentUser();
        return courseService.getCourseByInstructor(currentUser);
    }
}
