package com.minilms.repository;

import com.minilms.entity.Course;
import com.minilms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByMentor(User mentor);
}
