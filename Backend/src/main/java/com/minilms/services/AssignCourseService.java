package com.minilms.services;

import com.minilms.dto.courseDto.AssignCourseRequest;
import com.minilms.entity.Course;
import com.minilms.entity.CourseAssignment;
import com.minilms.entity.Role;
import com.minilms.entity.User;
import com.minilms.exceptions.ResourceNotFound;
import com.minilms.exceptions.UnauthorizedUserAndRole;
import com.minilms.repository.CourseAssignmentRepository;
import com.minilms.repository.CourseRepository;
import com.minilms.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignCourseService {
    private final CourseAssignmentRepository courseAssignmentRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;


    public AssignCourseService(CourseAssignmentRepository courseAssignmentRepository, CourseRepository courseRepository, UserRepository userRepository) {
        this.courseAssignmentRepository = courseAssignmentRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    //assign the course
    public void assignCourseToStudents(AssignCourseRequest request){
        User mentor = userRepository.findById(request.getMentorId())
                .orElseThrow(()->new ResourceNotFound("Mentor not found"));

        if(mentor.getRole() != Role.MENTOR || !mentor.getApproved())
            throw new UnauthorizedUserAndRole("This mentor is not allowed to do changes");


        Course course = courseRepository.findById(request.getCourseId()).orElseThrow(
                ()->new ResourceNotFound("Course not found")
        );

        if(!course.getMentor().getId().equals(request.getMentorId()))
            throw new UnauthorizedUserAndRole("this mentor is allowed to change course");

        List<User> students =userRepository.findAllById(request.getStudentIds());
        for(User student : students){
            if(student.getRole() != Role.STUDENT)
                continue;

            boolean alreadyAssigned =
                    courseAssignmentRepository.existsByStudentAndCourse(student,course);

            if(alreadyAssigned) continue;

            CourseAssignment courseAssignment = new CourseAssignment();
            courseAssignment.setStudent(student);
            courseAssignment.setCourse(course);

            courseAssignmentRepository.save(courseAssignment);

        }


    }

    public  List<Course> getAssignedCourses(Long studentId)  {
        User student = userRepository.findById(studentId)
                .orElseThrow(()->new ResourceNotFound("student not found with id "+studentId));

        if(student.getRole()!=Role.STUDENT)
            throw new UnauthorizedUserAndRole("only students see courses");

        List<CourseAssignment> assignments =
                courseAssignmentRepository.findByStudentId(studentId);

        return assignments.stream()
                .map(CourseAssignment::getCourse)
                .collect(Collectors.toList());
    }
}
