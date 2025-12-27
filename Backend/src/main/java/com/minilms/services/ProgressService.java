package com.minilms.services;

import com.minilms.dto.CompletionChapterRequest;
import com.minilms.dto.ProgressSummaryDto;
import com.minilms.entity.*;
import com.minilms.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgressService {
    private final ChapterRepository chapterRepository;
    private final CourseAssignmentRepository courseAssignmentRepository;
    private final UserRepository userRepository;
    private final ProgressRepository progressRepository;
    private final CourseRepository courseRepository;

    public ProgressService(ChapterRepository chapterRepository, CourseAssignmentRepository courseAssignmentRepository, UserRepository userRepository, ProgressRepository progressRepository, CourseRepository courseRepository) {
        this.chapterRepository = chapterRepository;
        this.courseAssignmentRepository = courseAssignmentRepository;
        this.userRepository = userRepository;
        this.progressRepository = progressRepository;
        this.courseRepository = courseRepository;
    }

    public void completeChapter(CompletionChapterRequest request){
        User student = userRepository.findById(request.getStudentId())
                .orElseThrow(()->new RuntimeException("Student not found with id: "+request.getStudentId()));

        if(student.getRole() != Role.STUDENT)
            throw new RuntimeException("User is not student");

        Chapter chapter = chapterRepository.findById(request.getChapterId())
                .orElseThrow(()->new RuntimeException("Chapter not found"));

        Course course = chapter.getCourse();

        courseAssignmentRepository.findByStudentAndCourse(student,course)
                .orElseThrow(()->new RuntimeException("Student is not assigned to this course"));

        List<Chapter> chapters = chapterRepository.findByCourseOrderBySequenceOrderAsc(course);

        for(Chapter ch: chapters){
            if(ch.getSequenceOrder() < chapter.getSequenceOrder()){
                boolean completed =
                        progressRepository.findByStudentAndChapter(student,ch)
                                .map(Progress::getCompleted)
                                .orElse(false);

                if(!completed)
                    throw new RuntimeException("Previous chapter is not completed");
            }
        }

        Progress progress = progressRepository.findByStudentAndChapter(student,chapter)
                .orElse(new Progress());

        progress.setStudent(student);
        progress.setChapter(chapter);
        progress.setCompleted(true);
        progress.setCompletedAt(java.time.LocalDateTime.now());

        progressRepository.save(progress);
    }

    public ProgressSummaryDto getProgressSummary(Long studentId ,Long courseId){
        User student = userRepository.findById(studentId)
                .orElseThrow(()->new RuntimeException("Student not found"));

        Course course =courseRepository.findById(courseId)
                .orElseThrow(()->new RuntimeException("Course not found"));

        courseAssignmentRepository.findByStudentAndCourse(student,course)
                .orElseThrow(()->new RuntimeException("Student not assigned to this course"));

        int totalChapters = chapterRepository.findByCourseOrderBySequenceOrderAsc(course).size();
        int completedChapters = (int) progressRepository.findByStudent(student)
                .stream()
                .filter(p->p.getChapter().getCourse().getId().equals(courseId))
                .filter(Progress::getCompleted)
                .count();

        int percentage = totalChapters == 0 ? 0 : (completedChapters*100)/totalChapters;


        ProgressSummaryDto progressSummaryDto = new ProgressSummaryDto();
        progressSummaryDto.setCourseId(courseId);
        progressSummaryDto.setProgressPercentage(percentage);
        progressSummaryDto.setTotalChapters(totalChapters);
        progressSummaryDto.setCompletedChapters(completedChapters);
        progressSummaryDto.setCertificateUnlocked(percentage==100);

        return progressSummaryDto;
    }
}
