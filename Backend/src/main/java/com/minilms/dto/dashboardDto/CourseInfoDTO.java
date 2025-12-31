package com.minilms.dto.dashboardDto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class CourseInfoDTO {
    private String title;
    private String description;
    private MentorInfoDTO mentor;
    private double progress;
    private String status;
    private List<ChapterProgressDTO> chapters;
}
