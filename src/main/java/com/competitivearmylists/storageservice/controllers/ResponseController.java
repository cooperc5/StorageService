package com.competitivearmylists.storageservice.controllers;

import java.util.HashMap;
import java.util.Map;

import com.competitivearmylists.storageservice.entities.CompetitorEventResult;
import com.competitivearmylists.storageservice.exceptions.ResourceNotFoundException;
import com.competitivearmylists.storageservice.repositories.CompetitorEventResultRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cer")
public class ResponseController {

    @Autowired
    private CompetitorEventResultRepository competitorEventResultRepository;

    @GetMapping
    public Page<CompetitorEventResult> getAllCompetitorEventResults(Pageable pageable) {
        return competitorEventResultRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetitorEventResult> getCompetitorEventResultById(@PathVariable Long id)
            throws ResourceNotFoundException {
        CompetitorEventResult competitorEventResult = competitorEventResultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CompetitorEventResult", id));
        return ResponseEntity.ok().body(competitorEventResult);
    }

    @PostMapping
    public CompetitorEventResult createCompetitorEventResult(@Valid @RequestBody CompetitorEventResult competitorEventResult) {
        return competitorEventResultRepository.save(competitorEventResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompetitorEventResult> updateCompetitorEventResult(@PathVariable Long id,
                                                                             @Valid @RequestBody CompetitorEventResult competitorEventResultDetails)
            throws ResourceNotFoundException {
        CompetitorEventResult competitorEventResult = competitorEventResultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CompetitorEventResult", id));

        competitorEventResult.setResult(competitorEventResultDetails.getResult());
        competitorEventResult.setLastName(competitorEventResultDetails.getLastName());
        competitorEventResult.setFirstName(competitorEventResultDetails.getFirstName());
        competitorEventResult.setList(competitorEventResultDetails.getList());
        competitorEventResult.setEventName(competitorEventResultDetails.getEventName());
        competitorEventResult.setDate(competitorEventResultDetails.getDate());

        final CompetitorEventResult updatedCompetitorEventResult = competitorEventResultRepository.save(competitorEventResult);
        return ResponseEntity.ok(updatedCompetitorEventResult);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteCompetitorEventResult(@PathVariable Long id)
            throws ResourceNotFoundException {
        CompetitorEventResult competitorEventResult = competitorEventResultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CompetitorEventResult", id));

        competitorEventResultRepository.delete(competitorEventResult);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

