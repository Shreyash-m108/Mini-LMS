package com.minilms.services;

import com.minilms.dto.CreateCourseRequest;
import com.minilms.entity.Course;
import com.minilms.entity.Role;
import com.minilms.entity.User;
import com.minilms.repository.CourseRepository;
import com.minilms.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    final CourseRepository courseRepository;
    final UserRepository userRepository;

    public CourseService (CourseRepository courseRepository, UserRepository userRepository){
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public Course createCourseRequest(CreateCourseRequest request){
        //search for mentor
        User mentor = userRepository.findById(request.getMentorId())
        .orElseThrow(()->new RuntimeException("Mentor not found"));

        //checking role
        if(mentor.getRole() != Role.MENTOR)
            throw new RuntimeException("Only Mentors can create course");

        if(!mentor.getApproved())
            throw new RuntimeException("Mentor is not approved");

        Course course = new Course();
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setMentor(mentor);
        return courseRepository.save(course);

    }
}
