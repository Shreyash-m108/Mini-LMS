package com.minilms.dto.dashboardDto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DashboardDTO {
    private StudentInfoDTO student;
    private List<CourseInfoDTO> courses;
    private ProgressSummaryDTO progress;
}
