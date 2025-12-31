package com.minilms.repository;

import com.minilms.entity.Course;
import com.minilms.entity.CourseAssignment;
import com.minilms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseAssignmentRepository extends JpaRepository<CourseAssignment, Long> {
    List<CourseAssignment> findByStudentId(Long studentId);
    Optional<CourseAssignment>  findByStudentAndCourse(User student, Course course);
    boolean existsByStudentAndCourse(User student, Course course);


    int countByCourse(Course course);
}
