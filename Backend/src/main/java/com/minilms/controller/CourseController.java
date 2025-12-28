package com.minilms.controller;

import com.minilms.dto.courseDto.CreateCourseRequest;
import com.minilms.dto.courseDto.ViewCourseDTO;
import com.minilms.entity.Course;
import com.minilms.services.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    final CourseService courseService;
    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @PostMapping
    public Course createCourse(@RequestBody CreateCourseRequest request){
        return  courseService.createCourseRequest(request);
    }

    @GetMapping("/{studentId}")
    public List<ViewCourseDTO> getCourseByStudent(@RequestParam Long studentId){
        return courseService.getCoursesByStudent(studentId);
    }
}
