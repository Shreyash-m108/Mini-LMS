package com.minilms.services;

import com.minilms.dto.courseDto.CreateCourseRequest;
import com.minilms.dto.courseDto.ViewCourseDTO;
import com.minilms.entity.Course;
import com.minilms.entity.Role;
import com.minilms.entity.User;
import com.minilms.repository.CourseAssignmentRepository;
import com.minilms.repository.CourseRepository;
import com.minilms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final AssignCourseService assignCourseService;
    private final CourseAssignmentRepository courseAssignmentRepository;



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

    public List<ViewCourseDTO> getCoursesByStudent(Long studentId){
        List<Course> courses = assignCourseService.getAssignedCourses(studentId);
        return courses.stream()
                .map(course -> {
                    ViewCourseDTO dto = new ViewCourseDTO();
                    dto.setTitle(course.getTitle());
                    dto.setDescription(course.getDescription());
                    dto.setMentorName(course.getMentor().getFirstName());
                    dto.setChapters(course.getChapters());
                    int studentCount = courseAssignmentRepository.countByCourse(course);
                    dto.setStudentCount(studentCount);
                    dto.setCreatedAt(course.getCreatedAt());
                    return dto;
                })
                .toList();
    }
}
