package com.minilms.dto.dashboardDto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ChapterProgressDTO {
    private String chapterName;
    private String description;
    private String imageUrl;
    private String videoUrl;
    private boolean completed;
}
