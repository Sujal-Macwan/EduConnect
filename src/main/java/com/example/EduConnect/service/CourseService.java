package com.example.EduConnect.service;

import com.example.EduConnect.dto.CourseDTO;
import com.example.EduConnect.entity.Course;
import com.example.EduConnect.entity.User;

import java.util.List;

public interface CourseService {
    void addCourse(CourseDTO courseDTO);  // Accept CourseDTO to avoid handling entities in controller
    List<CourseDTO> getAllCourses();      // Return DTOs instead of entities
    CourseDTO getCourseById(Long id);     // Return DTO instead of entity
    CourseDTO convertToDTO(Course course);  // Convert entity to DTO
    List<Course> getCourseByInstructor(User instructor);
}
