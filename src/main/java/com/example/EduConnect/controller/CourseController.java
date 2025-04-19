package com.example.EduConnect.controller;
import com.example.EduConnect.dto.CourseDTO;
import com.example.EduConnect.entity.Course;
import com.example.EduConnect.model.ApiResponse;
import com.example.EduConnect.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@Valid
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<ApiResponse<Course>> createCourse(@RequestBody @Valid CourseDTO courseDTO) {
        Course course = courseService.createCourse(courseDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Course created successfully", course, true, HttpStatus.CREATED.value()));
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<CourseDTO>>> getAllCourses() {
        List<CourseDTO> courses = courseService.getAllCourses();
        return ResponseEntity.ok(
                new ApiResponse<>("Courses fetched successfully", courses, true, HttpStatus.OK.value())
        );
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('INSTRUCTOR','ADMIN')")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathVariable Long id) {
        CourseDTO courseDTO = courseService.getCourseById(id);
        Course course = new Course(courseDTO);
        return ResponseEntity.ok(
                new ApiResponse<>("Course fetched successfully", course, true, HttpStatus.OK.value())
        );
    }
}
