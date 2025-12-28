package com.minilms.repository;

import com.minilms.entity.Course;
import com.minilms.entity.CourseAssignment;
import com.minilms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseAssignmentRepository extends JpaRepository<CourseAssignment, Long> {
    List<CourseAssignment> findByStudent(User student);
    Optional<CourseAssignment>  findByStudentAndCourse(User student, Course course);

    int countByCourse(Course course);
}
