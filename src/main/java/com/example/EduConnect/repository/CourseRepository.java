package com.example.EduConnect.repository;

import com.example.EduConnect.entity.Course;
import com.example.EduConnect.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByTitle(String title);
    List<Course> findByInstructor(User instructor);
}
