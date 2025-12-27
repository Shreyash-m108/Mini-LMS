package com.minilms.controller;

import com.minilms.entity.Certificate;
import com.minilms.services.CertificateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/certificates")
public class CertificateController {
    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping
    public Certificate getCertificate(@RequestParam Long studentId, @RequestParam Long courseId){
        return certificateService.generateCertificate(studentId,courseId);
    }
}
