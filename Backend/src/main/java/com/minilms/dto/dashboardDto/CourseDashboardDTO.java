package com.minilms.dto.dashboardDto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class CourseDashboardDTO {
    private Long courseId;
    private String title;
    private String description;
    private MentorInfoDTO mentor;
    private long completedChapters;
    private int totalChapters;
    private double progress;
    private String status;

}
