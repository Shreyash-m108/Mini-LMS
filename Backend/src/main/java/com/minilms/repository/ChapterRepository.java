package com.minilms.repository;

import com.minilms.entity.Chapter;
import com.minilms.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    List<Chapter> findByCourseOrderBySequenceOrderAsc(Course course);
}
