package com.minilms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompletionChapterRequest {
    private Long studentId;
    private Long chapterId;
}
