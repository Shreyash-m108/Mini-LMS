package com.minilms.controller;

import com.minilms.dto.ProgressSummaryDto;
import com.minilms.services.ProgressService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {
    private final ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @GetMapping("/summary")
    public ProgressSummaryDto getProgress(@RequestParam Long studentId,@RequestParam Long courseId){
        return progressService.getProgressSummary(studentId,courseId);
    }
}
