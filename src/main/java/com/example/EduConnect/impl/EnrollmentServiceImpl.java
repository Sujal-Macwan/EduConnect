package com.example.EduConnect.impl;

import com.example.EduConnect.entity.Enrollment;
import com.example.EduConnect.repository.EnrollmentRepository;
import com.example.EduConnect.service.EnrollmentService;

import java.util.List;

public class EnrollmentServiceImpl implements EnrollmentService{
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository){
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public void enrollUser(Enrollment enrollment){
        enrollmentRepository.save(enrollment);
    }

    @Override
    public List<Enrollment> getAllEnrollment(){
        return enrollmentRepository.findAll();
    }

    @Override
    public Enrollment getEnrollmentById(Long id){
        return enrollmentRepository.findById(id).orElse(null);
    }
}
