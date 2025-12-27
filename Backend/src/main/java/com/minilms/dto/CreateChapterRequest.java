package com.minilms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateChapterRequest {
    private Long courseId;
    private Long mentorId;
    private String title;
    private String description;
    private String imageUrl;
    private String videoUrl;
    private Integer sequenceOrder;
}
