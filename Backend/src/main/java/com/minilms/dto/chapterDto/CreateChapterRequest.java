package com.minilms.dto.chapterDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateChapterRequest {
    private Long courseId;
    private String title;
    private String description;
    private String imageUrl;
    private String videoUrl;
    private Integer sequenceOrder;
}
