package com.example.EduConnect.repository;

import com.example.EduConnect.entity.Course;
import com.example.EduConnect.entity.Enrollment;
import com.example.EduConnect.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    Optional<Enrollment> findByUserAndCourse(User user, Course course);
}
