package com.hirehub.hirehub.controller;

import com.hirehub.hirehub.model.Application;
import com.hirehub.hirehub.model.Job;
import com.hirehub.hirehub.model.User;
import com.hirehub.hirehub.repository.ApplicationRepository;
import com.hirehub.hirehub.repository.JobRepository;
import com.hirehub.hirehub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;

    // GET all users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    // GET all applications
    @GetMapping("/applications")
    public ResponseEntity<List<Application>> getAllApplications() {
        return ResponseEntity.ok(applicationRepository.findAll());
    }

    // GET dashboard stats
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> getStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("totalUsers", userRepository.count());
        stats.put("totalJobs", jobRepository.count());
        stats.put("totalApplications", applicationRepository.count());
        return ResponseEntity.ok(stats);
    }

    // DELETE any job
    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        if (!jobRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        jobRepository.deleteById(id);
        return ResponseEntity.ok("Job deleted by admin!");
    }

    // DELETE any user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted by admin!");
    }
}