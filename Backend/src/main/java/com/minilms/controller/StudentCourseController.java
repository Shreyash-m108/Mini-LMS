package com.minilms.controller;

import com.minilms.dto.ChapterViewDto;
import com.minilms.entity.Chapter;
import com.minilms.services.StudentCourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentCourseController {
    private final StudentCourseService studentCourseService;


    public StudentCourseController(StudentCourseService studentCourseService) {
        this.studentCourseService = studentCourseService;
    }

    @GetMapping("/courses/{courseId}/chapters")
    public List<ChapterViewDto> getChapters(@PathVariable Long courseId, @RequestParam Long studentId){
        return studentCourseService.getCourseChapters(studentId,courseId);
    }
}
