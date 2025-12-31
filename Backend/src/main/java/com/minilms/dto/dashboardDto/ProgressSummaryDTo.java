package com.minilms.dto.dashboardDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProgressSummaryDTO {
    private int totalCourses;
    private int completedCourses;
    private double overallProgress;
}
