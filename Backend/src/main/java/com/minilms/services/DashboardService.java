package com.minilms.services;

import com.minilms.dto.dashboardDto.*;
import com.minilms.entity.Chapter;
import com.minilms.entity.Course;
import com.minilms.entity.CourseAssignment;
import com.minilms.entity.User;
import com.minilms.exceptions.ResourceNotFound;
import com.minilms.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final ChapterRepository chapterRepository;
    private final ProgressRepository progressRepository;
    private final CourseAssignmentRepository courseAssignmentRepository;

    public DashboardDTO getDashboard(Long studentId) {

        User student = userRepository.findById(studentId)
                .orElseThrow(()-> new ResourceNotFound("student not found with id "+studentId));


        //student dto
        StudentInfoDTO studentInfoDTO = new StudentInfoDTO();
        studentInfoDTO.setId(student.getId());
        studentInfoDTO.setFirstName(student.getFirstName());
        studentInfoDTO.setLastName(student.getLastName());
        studentInfoDTO.setMiddleName(student.getMiddleName());
        studentInfoDTO.setEmail(student.getEmail());

        List<CourseAssignment> assignments =
                courseAssignmentRepository.findByStudentId(studentId);

        List<CourseDashboardDTO> courseDashboardList = new ArrayList<>();

        for(CourseAssignment assignment : assignments){
            Course course = assignment.getCourse();
            User mentor = course.getMentor();

            //mentor dto
            MentorInfoDTO mentorInfoDTO = new MentorInfoDTO();
            mentorInfoDTO.setId(mentor.getId());
            mentorInfoDTO.setFirstName(mentor.getFirstName());
            mentorInfoDTO.setLastName(mentor.getLastName());

            List<Chapter> chaptersList = chapterRepository.
                    findByCourseOrderBySequenceOrderAsc(course);

            int totalChapters =  chaptersList.size();
            long completedChapters =
                    progressRepository.countByStudent_IdAndChapter_Course_IdAndCompletedTrue(
                            studentId , course.getId()
                    );
            double percentage = totalChapters == 0 ? 0 :
                    ((double) (completedChapters * 100) / totalChapters);

            //course dashboard dto
            CourseDashboardDTO courseDashboardDTO = new CourseDashboardDTO();
            courseDashboardDTO.setCourseId(course.getId());
            courseDashboardDTO.setTitle(course.getTitle());
            courseDashboardDTO.setDescription(course.getDescription());
            courseDashboardDTO.setMentor(mentorInfoDTO);
            courseDashboardDTO.setProgress(percentage);
            courseDashboardDTO.setTotalChapters(totalChapters);
            courseDashboardDTO.setCompletedChapters(completedChapters);

            courseDashboardList.add(courseDashboardDTO);
        }

        ProgressSummaryDTO progressSummaryDTO = new ProgressSummaryDTO();
        progressSummaryDTO.setTotalCourses(courseDashboardList.size());
        long completedCourses = courseDashboardList.stream()
                .filter(c->c.getProgress() == 100).count();

        progressSummaryDTO.setCompletedCourses((int) completedCourses);
        progressSummaryDTO.setInProgress(  courseDashboardList.size() - (int) completedCourses);

        DashboardDTO dashboardDTO = new DashboardDTO();
        dashboardDTO.setStudent(studentInfoDTO);
        dashboardDTO.setCourses(courseDashboardList);
        dashboardDTO.setProgress(progressSummaryDTO);

        return  dashboardDTO;
    }


}
