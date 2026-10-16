package com.example.recruitflow.candidate.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "candidates", uniqueConstraints = {
        @UniqueConstraint(name = "uk_candidates_email", columnNames = "email")
})
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 120)
    private String fullName;

    @Column(nullable = false, unique = true, length = 180)
    private String email;

    @Column(length = 20)
    private String phone;

    @Column(length = 500)
    private String skills;

    @Column(name = "experience_years", nullable = false)
    private Integer experienceYears;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    protected Candidate() {
        // Required by JPA.
    }

    public Candidate(String fullName, String email, String phone, String skills, Integer experienceYears) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.skills = skills;
        this.experienceYears = experienceYears;
    }

    @PrePersist
    void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getSkills() { return skills; }
    public Integer getExperienceYears() { return experienceYears; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void updateDetails(String fullName, String email, String phone, String skills, Integer experienceYears) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.skills = skills;
        this.experienceYears = experienceYears;
    }
}
