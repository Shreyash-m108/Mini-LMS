package com.minilms.repository;

import com.minilms.entity.Certificate;
import com.minilms.entity.Course;
import com.minilms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    Optional<Certificate> findByStudentAndCourse(User Student, Course course);

}
