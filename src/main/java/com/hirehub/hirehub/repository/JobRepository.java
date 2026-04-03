package com.hirehub.hirehub.repository;

import com.hirehub.hirehub.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByTitleContainingIgnoreCase(String title);
    List<Job> findByLocation(String location);
    List<Job> findByType(String type);
}