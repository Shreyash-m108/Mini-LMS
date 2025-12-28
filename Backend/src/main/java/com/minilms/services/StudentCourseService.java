package com.minilms.services;

import com.minilms.dto.chapterDto.ChapterViewDto;
import com.minilms.entity.Chapter;
import com.minilms.entity.Course;
import com.minilms.entity.Progress;
import com.minilms.entity.User;
import com.minilms.exceptions.ResourceNotFound;
import com.minilms.repository.ChapterRepository;
import com.minilms.repository.CourseRepository;
import com.minilms.repository.ProgressRepository;
import com.minilms.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentCourseService {
    private final ChapterRepository chapterRepository;
    private final ProgressRepository progressRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public StudentCourseService(ChapterRepository chapterRepository, ProgressRepository progressRepository, CourseRepository courseRepository, UserRepository userRepository) {
        this.chapterRepository = chapterRepository;
        this.progressRepository = progressRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public List<ChapterViewDto> getCourseChapters(Long studentId, Long courseId){
        User student = userRepository.findById(studentId)
                .orElseThrow(()->new ResourceNotFound("Student not found with id: "+studentId));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(()-> new ResourceNotFound("Course not found"));

        List<Chapter> chapters = chapterRepository.findByCourseOrderBySequenceOrderAsc(course);

        List<ChapterViewDto> result = new ArrayList<>();

        boolean unlockNext =true;

        for(Chapter chapter :chapters){
            boolean completed =
                    progressRepository.findByStudentAndChapter(student,chapter)
                            .map(Progress::getCompleted)
                            .orElse(false);

            ChapterViewDto chapterViewDto = new ChapterViewDto();
            chapterViewDto.setChapterId(chapter.getId());
            chapterViewDto.setTitle(chapter.getTitle());
            chapterViewDto.setDescription(chapter.getDescription());
            chapterViewDto.setVideoUrl(chapter.getVideoUrl());
            chapterViewDto.setUnlocked(unlockNext);
            chapterViewDto.setCompleted(completed);

            result.add(chapterViewDto);
            unlockNext = completed;
        }
        return result;
    }
}
