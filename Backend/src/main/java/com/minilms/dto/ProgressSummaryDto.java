package com.minilms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProgressSummaryDto {
    private Long courseId;
    private int totalChapters;
    private int completedChapters;
    private int progressPercentage;
    private boolean certificateUnlocked;

}
