package com.example.EduConnect.service;

import com.example.EduConnect.entity.Enrollment;

import java.util.List;

public interface EnrollmentService {
    List<Enrollment> getAllEnrollment();
    Enrollment getEnrollmentById(Long id);
    Enrollment enrollUserInCourse(Long courseId);
}
