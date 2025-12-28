package com.minilms.dto.chapterDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompletionChapterRequest {
    private Long studentId;
    private Long chapterId;
}
