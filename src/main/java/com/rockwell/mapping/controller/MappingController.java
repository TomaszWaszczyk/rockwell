package com.rockwell.mapping.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rockwell.mapping.dto.MappingRequest;
import com.rockwell.mapping.dto.MappingResponse;
import com.rockwell.mapping.service.MappingService;

@RestController
@RequestMapping("/api/mapping")
public class MappingController {

    private final MappingService mappingService;

    public MappingController(MappingService mappingService) {
        this.mappingService = mappingService;
    }

    @PostMapping("/process")
    public ResponseEntity<MappingResponse> processMapping(@RequestBody MappingRequest request) {
        try {
            validateRequest(request);
            MappingResponse response = mappingService.processMapping(
                request.getMappingName(), 
                request.getNumbers()
            );
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/available")
    public ResponseEntity<List<String>> getAvailableMappings() {
        return ResponseEntity.ok(mappingService.getAvailableMappings());
    }

    private void validateRequest(MappingRequest request) {
        if (request.getMappingName() == null || request.getMappingName().trim().isEmpty()) {
            throw new IllegalArgumentException("Mapping name is required");
        }
        if (request.getNumbers() == null || request.getNumbers().isEmpty()) {
            throw new IllegalArgumentException("Numbers list cannot be empty");
        }
        for (Integer number : request.getNumbers()) {
            if (number == null || number < 1 || number > 20) {
                throw new IllegalArgumentException("Numbers must be in range 1-20");
            }
        }
    }
}

