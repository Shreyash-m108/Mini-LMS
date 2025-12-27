package com.minilms.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChapterViewDto {
    private Long chapterId;
    private String title;
    private String description;
    private String videoUrl;
     private boolean completed;
     private boolean unlocked;
}
