package com.minilms.controller;

import com.minilms.dto.CreateChapterRequest;
import com.minilms.entity.Chapter;
import com.minilms.services.ChapterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chapters")
public class ChapterController {
    private final ChapterService chapterService;

    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @PostMapping
    public Chapter addChapter (@RequestBody CreateChapterRequest request){
        return chapterService.addChapter(request);
    }
}
