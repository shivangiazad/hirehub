package com.hirehub.hirehub.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long jobId;
    private String studentEmail;
    private String resumeUrl;

    @Enumerated(EnumType.STRING)
    private Status status = Status.APPLIED;

    private LocalDateTime appliedAt = LocalDateTime.now();

    public enum Status {
        APPLIED, SHORTLISTED, REJECTED
    }
}