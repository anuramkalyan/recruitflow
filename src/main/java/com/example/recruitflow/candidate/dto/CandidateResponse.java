package com.example.recruitflow.candidate.dto;

import com.example.recruitflow.candidate.entity.Candidate;
import java.time.LocalDateTime;

public record CandidateResponse(
        Long id,
        String fullName,
        String email,
        String phone,
        String skills,
        Integer experienceYears,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static CandidateResponse from(Candidate candidate) {
        return new CandidateResponse(
                candidate.getId(),
                candidate.getFullName(),
                candidate.getEmail(),
                candidate.getPhone(),
                candidate.getSkills(),
                candidate.getExperienceYears(),
                candidate.getCreatedAt(),
                candidate.getUpdatedAt()
        );
    }
}
