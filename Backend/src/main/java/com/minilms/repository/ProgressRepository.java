package com.minilms.repository;

import com.minilms.entity.Chapter;
import com.minilms.entity.Progress;
import com.minilms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
    List<Progress> findByStudent(User student);
    Optional<Progress> findByStudentAndChapter(User student, Chapter chapter);
}
