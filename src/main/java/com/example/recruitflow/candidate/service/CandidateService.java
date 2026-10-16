package com.example.recruitflow.candidate.service;

import com.example.recruitflow.candidate.dto.CandidateRequest;
import com.example.recruitflow.candidate.dto.CandidateResponse;
import com.example.recruitflow.candidate.entity.Candidate;
import com.example.recruitflow.candidate.repository.CandidateRepository;
import com.example.recruitflow.common.exception.DuplicateResourceException;
import com.example.recruitflow.common.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CandidateService {
    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public CandidateResponse create(CandidateRequest request) {
        if (candidateRepository.existsByEmailIgnoreCase(request.email())) {
            throw new DuplicateResourceException("A candidate with this email already exists");
        }

        Candidate candidate = new Candidate(
                request.fullName().trim(),
                request.email().trim().toLowerCase(),
                request.phone(),
                request.skills(),
                request.experienceYears()
        );
        return CandidateResponse.from(candidateRepository.save(candidate));
    }

    @Transactional(readOnly = true)
    public CandidateResponse getById(Long id) {
        return CandidateResponse.from(findCandidate(id));
    }

    @Transactional(readOnly = true)
    public Page<CandidateResponse> list(Pageable pageable) {
        return candidateRepository.findAll(pageable).map(CandidateResponse::from);
    }

    public CandidateResponse update(Long id, CandidateRequest request) {
        Candidate candidate = findCandidate(id);
        if (candidateRepository.existsByEmailIgnoreCaseAndIdNot(request.email(), id)) {
            throw new DuplicateResourceException("Another candidate already uses this email");
        }

        candidate.updateDetails(
                request.fullName().trim(),
                request.email().trim().toLowerCase(),
                request.phone(),
                request.skills(),
                request.experienceYears()
        );
        return CandidateResponse.from(candidateRepository.save(candidate));
    }

    public void delete(Long id) {
        Candidate candidate = findCandidate(id);
        candidateRepository.delete(candidate);
    }

    private Candidate findCandidate(Long id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found with id: " + id));
    }
}
