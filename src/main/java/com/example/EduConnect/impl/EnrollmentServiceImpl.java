package com.example.EduConnect.impl;

import com.example.EduConnect.dto.CourseDTO;
import com.example.EduConnect.entity.Course;
import com.example.EduConnect.entity.Enrollment;
import com.example.EduConnect.entity.User;
import com.example.EduConnect.exception.ResourceNotFoundException;
import com.example.EduConnect.repository.EnrollmentRepository;
import com.example.EduConnect.service.CourseService;
import com.example.EduConnect.service.EnrollmentService;
import com.example.EduConnect.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentServiceImpl implements EnrollmentService{
    private final EnrollmentRepository enrollmentRepository;
    private final UserService userService;
    private final CourseService courseService;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository, UserService userService, CourseService courseService){
        this.enrollmentRepository = enrollmentRepository;
        this.userService = userService;
        this.courseService = courseService;
    }

    @Override
    public Enrollment enrollUserInCourse(Long courseId){
        User student = userService.getCurrentUser();

        CourseDTO courseDTO = courseService.getCourseById(courseId);
        Course course = new Course(courseDTO);

        Optional<Enrollment> existingEnrollment = enrollmentRepository.findByUserAndCourse(student, course);
        if(existingEnrollment.isPresent()){
            throw new IllegalStateException("Student is already enrolled in this course.");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setUser(student);
        enrollment.setCourse(course);
        enrollment.setEnrolledAt(LocalDateTime.now());

        return enrollmentRepository.save(enrollment);
    }

    @Override
    public List<Enrollment> getAllEnrollment(){
        return enrollmentRepository.findAll();
    }

    @Override
    public Enrollment getEnrollmentById(Long id){
        return enrollmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + id));
    }
}
