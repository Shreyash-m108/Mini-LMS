package com.minilms.services;

import com.minilms.dto.AssignCourseRequest;
import com.minilms.entity.Course;
import com.minilms.entity.CourseAssignment;
import com.minilms.entity.Role;
import com.minilms.entity.User;
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
    public void assignCourseToStudent(AssignCourseRequest request){
        User mentor = userRepository.findById(request.getMentorId())
                .orElseThrow(()->new RuntimeException("Mentor not found"));

        if(mentor.getRole() != Role.MENTOR || !mentor.getApproved())
            throw new RuntimeException("This mentor is not allowed to do changes");


        Course course = courseRepository.findById(request.getCourseId()).orElseThrow(
                ()->new RuntimeException("Course nit found")
        );

        if(!course.getMentor().getId().equals(request.getMentorId()))
            throw new RuntimeException("Mentor is allowed to change course");


        for(Long studentId: request.getStudentIds()){
            User student =userRepository.findById(studentId)
                    .orElseThrow(()->new RuntimeException("Student not found"));

            if(student.getRole() != Role.STUDENT)
                throw new RuntimeException("This is not student: "+studentId);

            boolean alreadyAssigned =
                    courseAssignmentRepository.findByStudentAndCourse(student,course).isPresent();

            if(alreadyAssigned){
                continue;
            }

            CourseAssignment assignment = new CourseAssignment();
            assignment.setStudent(student);
            assignment.setCourse(course);

            courseAssignmentRepository.save(assignment);
        }

    }

    public  List<Course> getAssignedCourses(Long studentId){
        User student = userRepository.findById(studentId)
                .orElseThrow(()->new RuntimeException("Student not found with id: "+studentId));
        if(student.getRole()!=Role.STUDENT)
            throw new RuntimeException("Student is not valid");

        List<CourseAssignment> assignments =
                courseAssignmentRepository.findByStudent(student);

        return assignments.stream()
                .map(CourseAssignment::getCourse)
                .collect(Collectors.toList());
    }
}
