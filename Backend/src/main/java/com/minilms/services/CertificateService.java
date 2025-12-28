package com.minilms.services;

import com.minilms.dto.pogressDto.ProgressSummaryDto;
import com.minilms.entity.Certificate;
import com.minilms.entity.Course;
import com.minilms.entity.User;
import com.minilms.exceptions.ResourceNotFound;
import com.minilms.repository.CertificateRepository;
import com.minilms.repository.CourseRepository;
import com.minilms.repository.ProgressRepository;
import com.minilms.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CertificateService {
    private final CertificateRepository certificateRepository;
    private final CourseRepository  courseRepository;
    private final UserRepository userRepository;
    private final ProgressService progressService;

    public CertificateService(CertificateRepository certificateRepository, CourseRepository courseRepository, ProgressRepository progressRepository, UserRepository userRepository, ProgressService progressService) {
        this.certificateRepository = certificateRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.progressService = progressService;
    }

    public Certificate generateCertificate(Long studentId, Long courseId){
        ProgressSummaryDto summary = progressService.getProgressSummary(studentId,courseId);

        if(!summary.isCertificateUnlocked())
            throw new RuntimeException("Course is not completed yet.");

        User student = userRepository.findById(studentId)
                .orElseThrow(()->new ResourceNotFound("student is not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(()->new ResourceNotFound("course is not found"));

        return certificateRepository
                .findByStudentAndCourse(student, course)
                .orElseGet(()->{
                    Certificate certificate = new Certificate();
                    certificate.setStudent(student);
                    certificate.setCourse(course);
                    return certificateRepository.save(certificate);
                });


    }
}
