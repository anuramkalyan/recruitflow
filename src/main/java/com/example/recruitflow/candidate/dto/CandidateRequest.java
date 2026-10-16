package com.example.recruitflow.candidate.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CandidateRequest(
        @NotBlank(message = "Full name is required")
        @Size(max = 120, message = "Full name must be at most 120 characters")
        String fullName,

        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        @Size(max = 180, message = "Email must be at most 180 characters")
        String email,

        @Size(max = 20, message = "Phone must be at most 20 characters")
        String phone,

        @Size(max = 500, message = "Skills must be at most 500 characters")
        String skills,

        @NotNull(message = "Experience years is required")
        @Min(value = 0, message = "Experience years cannot be negative")
        Integer experienceYears
) {}
