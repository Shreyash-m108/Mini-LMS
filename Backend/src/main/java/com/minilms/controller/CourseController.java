package com.minilms.controller;

import com.minilms.dto.CreateCourseRequest;
import com.minilms.entity.Course;
import com.minilms.services.CourseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
