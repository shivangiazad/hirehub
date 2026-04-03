package com.hirehub.hirehub.controller;

import com.hirehub.hirehub.model.Application;
import com.hirehub.hirehub.repository.ApplicationRepository;
import com.hirehub.hirehub.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;

    // POST apply for a job with resume upload
    @PostMapping("/jobs/{jobId}/apply")
    public ResponseEntity<String> applyForJob(
            @PathVariable Long jobId,
            @RequestParam("resume") MultipartFile resume,
            Authentication auth) throws IOException {

        // Check if job exists
        if (!jobRepository.existsById(jobId)) {
            return ResponseEntity.badRequest().body("Job not found!");
        }

        // Check if already applied
        List<Application> existing = applicationRepository.findByJobId(jobId);
        boolean alreadyApplied = existing.stream()
                .anyMatch(a -> a.getStudentEmail().equals(auth.getName()));
        if (alreadyApplied) {
            return ResponseEntity.badRequest().body("You already applied for this job!");
        }

        // Save resume file to uploads folder
        String uploadDir = "uploads/";
        Files.createDirectories(Paths.get(uploadDir));
        String fileName = auth.getName() + "_" + jobId + "_" + resume.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);
        Files.copy(resume.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Save application to DB
        Application application = new Application();
        application.setJobId(jobId);
        application.setStudentEmail(auth.getName());
        application.setResumeUrl(filePath.toString());
        applicationRepository.save(application);

        return ResponseEntity.ok("Applied successfully!");
    }

    // GET all applicants for a job (company sees this)
    @GetMapping("/jobs/{jobId}/applicants")
    public ResponseEntity<List<Application>> getApplicants(
            @PathVariable Long jobId,
            Authentication auth) {
        return ResponseEntity.ok(applicationRepository.findByJobId(jobId));
    }

    // PUT update application status (company shortlists/rejects)
    @PutMapping("/applications/{id}/status")
    public ResponseEntity<String> updateStatus(
            @PathVariable Long id,
            @RequestParam String status,
            Authentication auth) {
        return applicationRepository.findById(id).map(application -> {
            application.setStatus(Application.Status.valueOf(status.toUpperCase()));
            applicationRepository.save(application);
            return ResponseEntity.ok("Status updated to " + status);
        }).orElse(ResponseEntity.notFound().build());
    }

    // GET my applications (student sees their own)
    @GetMapping("/my/applications")
    public ResponseEntity<List<Application>> myApplications(Authentication auth) {
        return ResponseEntity.ok(
            applicationRepository.findByStudentEmail(auth.getName())
        );
    }
}
