package com.minilms.services;

import com.minilms.dto.CreateChapterRequest;
import com.minilms.entity.Chapter;
import com.minilms.entity.Course;
import com.minilms.entity.Role;
import com.minilms.entity.User;
import com.minilms.repository.ChapterRepository;
import com.minilms.repository.CourseRepository;
import com.minilms.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterService {
    private final ChapterRepository chapterRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public ChapterService(ChapterRepository chapterRepository, CourseRepository courseRepository,UserRepository userRepository){
        this.chapterRepository = chapterRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public Chapter addChapter (CreateChapterRequest request){
        User mentor = userRepository.findById(request.getMentorId())
                .orElseThrow(()->new RuntimeException("Mentor not found."));

        if(mentor.getRole() != Role.MENTOR)
            throw new RuntimeException("Only Mentors can add the course");

        if(!mentor.getApproved())
            throw new RuntimeException("Mentor is not approved");

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(()->new RuntimeException("Course not found"));

        if(!course.getMentor().getId().equals(request.getMentorId()))
            throw new RuntimeException("You have no right to change this course");

        List<Chapter> existingChapters =
                chapterRepository.findByCourseOrderBySequenceOrderAsc(course);

        boolean sequenceExists = existingChapters.stream()
                .anyMatch(c->c.getSequenceOrder().equals(request.getSequenceOrder()));

        if(sequenceExists)
            throw new RuntimeException("This chapter is already exists.");

        Chapter chapter = new Chapter();
        chapter.setTitle(request.getTitle());
        chapter.setDescription(request.getDescription());
        chapter.setCourse(course);
        chapter.setImageUrl(request.getImageUrl());
        chapter.setVideoUrl(request.getVideoUrl());
        chapter.setSequenceOrder(request.getSequenceOrder());

        return chapterRepository.save(chapter);
    }




}
