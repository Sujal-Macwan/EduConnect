package com.example.EduConnect.controller;

import com.example.EduConnect.entity.Enrollment;
import com.example.EduConnect.service.EnrollmentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    private EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService){
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public List<Enrollment> getAllEnrollments(){
        return enrollmentService.getAllEnrollment();
    }

    @PostMapping
    public void addEnrollment(@RequestBody Enrollment enrollment){
        enrollmentService.enrollUser(enrollment);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public Enrollment getEnrollmentById(@PathVariable Long id){
        return enrollmentService.getEnrollmentById(id);
    }
}
