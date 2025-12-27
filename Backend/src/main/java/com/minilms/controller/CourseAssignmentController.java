package com.minilms.controller;

import com.minilms.dto.AssignCourseRequest;
import com.minilms.entity.Course;
import com.minilms.services.AssignCourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseAssignmentController {
    private final AssignCourseService assignCourseService;

    public CourseAssignmentController(AssignCourseService assignCourseService) {
        this.assignCourseService = assignCourseService;
    }

    @PostMapping("/assign")
    public String courseAssignment(@RequestBody AssignCourseRequest request){
        assignCourseService.assignCourseToStudent(request);
        return "Course assigned Successfully";
    }

    @GetMapping("/my")
    public List<Course> getMyCourses(@RequestParam Long studentId){
        return assignCourseService.getAssignedCourses(studentId);
    }
}
