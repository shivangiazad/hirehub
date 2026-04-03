package com.hirehub.hirehub.controller;

import com.hirehub.hirehub.model.Job;
import com.hirehub.hirehub.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobRepository jobRepository;

    // GET all jobs with pagination
    @GetMapping
    public ResponseEntity<Page<Job>> getAllJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(jobRepository.findAll(pageable));
    }

    // GET single job by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getJobById(@PathVariable Long id) {
        return jobRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET search jobs by title
    @GetMapping("/search")
    public ResponseEntity<List<Job>> searchJobs(@RequestParam String title) {
        return ResponseEntity.ok(
            jobRepository.findByTitleContainingIgnoreCase(title)
        );
    }

    // POST create job - only logged in users
    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job,
                                            Authentication auth) {
        job.setPostedByEmail(auth.getName());
        jobRepository.save(job);
        return ResponseEntity.ok("Job posted successfully!");
    }

    // PUT update job
    @PutMapping("/{id}")
    public ResponseEntity<?> updateJob(@PathVariable Long id,
                                        @RequestBody Job updatedJob,
                                        Authentication auth) {
        return jobRepository.findById(id).map(job -> {
            if (!job.getPostedByEmail().equals(auth.getName())) {
                return ResponseEntity.status(403)
                    .body("You can only edit your own jobs!");
            }
            job.setTitle(updatedJob.getTitle());
            job.setCompany(updatedJob.getCompany());
            job.setLocation(updatedJob.getLocation());
            job.setType(updatedJob.getType());
            job.setSalary(updatedJob.getSalary());
            job.setDescription(updatedJob.getDescription());
            jobRepository.save(job);
            return ResponseEntity.ok("Job updated successfully!");
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE job
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable Long id,
                                        Authentication auth) {
        return jobRepository.findById(id).map(job -> {
            if (!job.getPostedByEmail().equals(auth.getName())) {
                return ResponseEntity.status(403)
                    .body("You can only delete your own jobs!");
            }
            jobRepository.delete(job);
            return ResponseEntity.ok("Job deleted successfully!");
        }).orElse(ResponseEntity.notFound().build());
    }
}