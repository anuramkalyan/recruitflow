package com.example.recruitflow.candidate.repository;

import com.example.recruitflow.candidate.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    boolean existsByEmailIgnoreCase(String email);
    Optional<Candidate> findByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);
}
