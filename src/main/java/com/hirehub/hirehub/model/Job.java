package com.hirehub.hirehub.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String company;
    private String location;
    private String type; // Full-time, Internship, Part-time
    private String salary;

    @Column(length = 2000)
    private String description;

    private String postedByEmail;

    private LocalDateTime createdAt = LocalDateTime.now();
}