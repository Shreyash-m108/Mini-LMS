package com.minilms.services;

import com.minilms.dto.dashboardDto.*;
import com.minilms.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final ChapterRepository chapterRepository;
    private final ProgressRepository progressRepository;
    private final CourseAssignmentRepository courseAssignmentRepository;



    public DashboardDTO mockDashboard(){
        DashboardDTO dashboardDTO = new DashboardDTO();
        //student DTO
        StudentInfoDTO studentInfoDTO = new StudentInfoDTO();
        studentInfoDTO.setEmail("luffy@op.com");
        studentInfoDTO.setFirstName("Luffy");
        studentInfoDTO.setMiddleName("D");
        studentInfoDTO.setLastName("Monkey");


        MentorInfoDTO mentorInfoDTO = new MentorInfoDTO();
        mentorInfoDTO.setMentorName("Silvers Reyleigh");

        ChapterProgressDTO chapterProgressDTO1 = new ChapterProgressDTO();
        chapterProgressDTO1.setChapterName("Ornament Haki");
        chapterProgressDTO1.setDescription("Basics about ornament haki.");
        chapterProgressDTO1.setImageUrl("http");
        chapterProgressDTO1.setVideoUrl("http");
        chapterProgressDTO1.setCompleted(true);

        ChapterProgressDTO chapterProgressDTO2 = new ChapterProgressDTO();
        chapterProgressDTO2.setChapterName("Observation Haki");
        chapterProgressDTO2.setDescription("Basics about observation haki.");
        chapterProgressDTO2.setImageUrl("http");
        chapterProgressDTO2.setVideoUrl("http");
        chapterProgressDTO2.setCompleted(false);

        ChapterProgressDTO chapterProgressDTO3 = new ChapterProgressDTO();
        chapterProgressDTO3.setChapterName("Conquerers Haki");
        chapterProgressDTO3.setDescription("Basics about conquerers haki.");
        chapterProgressDTO3.setImageUrl("http");
        chapterProgressDTO3.setVideoUrl("http");
        chapterProgressDTO3.setCompleted(false);

        CourseInfoDTO courseInfoDTO = new CourseInfoDTO();
        courseInfoDTO.setTitle("Haki");
        courseInfoDTO.setDescription("Overall about haki and how to use haki");
        courseInfoDTO.setChapters(List.of(chapterProgressDTO1,chapterProgressDTO2,chapterProgressDTO3));
        courseInfoDTO.setStatus("ONGOING");
        courseInfoDTO.setProgress(33.33);
        courseInfoDTO.setMentor(mentorInfoDTO);

        ProgressSummaryDTO progressSummaryDTO = new ProgressSummaryDTO();
        progressSummaryDTO.setTotalCourses(1);
        progressSummaryDTO.setCompletedCourses(0);
        progressSummaryDTO.setOverallProgress(courseInfoDTO.getProgress());

        dashboardDTO.setStudent(studentInfoDTO);
        dashboardDTO.setCourses(List.of(courseInfoDTO));
        dashboardDTO.setProgress(progressSummaryDTO);
        return dashboardDTO;
    }

}
