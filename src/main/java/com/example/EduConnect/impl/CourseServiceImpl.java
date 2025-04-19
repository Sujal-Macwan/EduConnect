package com.example.EduConnect.impl;

import com.example.EduConnect.dto.CourseDTO;
import com.example.EduConnect.entity.Course;
import com.example.EduConnect.entity.User;
import com.example.EduConnect.exception.ResourceNotFoundException;
import com.example.EduConnect.repository.CourseRepository;
import com.example.EduConnect.service.CourseService;
import com.example.EduConnect.service.UserService;
import org.hibernate.type.ListType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserService userService;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, UserService userService) {
        this.courseRepository = courseRepository;
        this.userService = userService;
    }

    @Override
    public Course createCourse(CourseDTO courseDTO) {
        User instructor =  userService.getCurrentUser();

        Course course = new Course();
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setCategory(courseDTO.getCategory());
        course.setInstructor(instructor);
        // Set createdAt in the entity is handled in prePersist
        courseRepository.save(course);
        return courseRepository.save(course);
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        return convertToDTO(course);
    }

    @Override
    public List<Course> getCourseByInstructor(User instructor){
        return courseRepository.findByInstructor(instructor);
    }

    @Override
    public CourseDTO convertToDTO(Course course) {
        return new CourseDTO(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getCategory(),
                course.getPrice()
        );
    }
}
