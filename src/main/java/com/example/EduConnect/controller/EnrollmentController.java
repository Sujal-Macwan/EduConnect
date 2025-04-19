package com.example.EduConnect.controller;

import com.example.EduConnect.entity.Course;
import com.example.EduConnect.entity.Enrollment;
import com.example.EduConnect.entity.User;
import com.example.EduConnect.model.ApiResponse;
import com.example.EduConnect.service.CourseService;
import com.example.EduConnect.service.EnrollmentService;
import com.example.EduConnect.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<List<Enrollment>>> getAllEnrollments(){
        List<Enrollment> enrollments = enrollmentService.getAllEnrollment();
        return ResponseEntity.ok(new ApiResponse<>("Enrollments fetched successfully", enrollments, true, HttpStatus.OK.value()));
    }

    @PostMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<Void>> addEnrollment(@RequestParam Long courseId) {
        enrollmentService.enrollUserInCourse(courseId);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>("Enrolled successfully", null, true, HttpStatus.CREATED.value())
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public ResponseEntity<ApiResponse<Enrollment>> getEnrollmentById(@PathVariable Long id) {
        Enrollment enrollment = enrollmentService.getEnrollmentById(id);
        return ResponseEntity.ok(
                new ApiResponse<>("Enrollment fetched successfully", enrollment, true, HttpStatus.OK.value())
        );
    }

    @GetMapping("/my-courses")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<ApiResponse<List<Course>>> getInstructorCourses() {
        User currentUser = userService.getCurrentUser();
        List<Course> courses = courseService.getCourseByInstructor(currentUser);
        return ResponseEntity.ok(
                new ApiResponse<>("Courses fetched successfully", courses, true, HttpStatus.OK.value())
        );
    }
}
