package com.minilms.controller;

import com.minilms.dto.dashboardDto.DashboardDTO;
import com.minilms.services.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping("/{studentId}")
    public DashboardDTO getDashboard(@RequestParam long studentId){
       return dashboardService.getDashboard(studentId);
    }
}
