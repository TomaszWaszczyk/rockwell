package com.rockwell.mapping.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(MappingController.class);
    private final MappingService mappingService;

    public MappingController(MappingService mappingService) {
        this.mappingService = mappingService;
    }

    @PostMapping("/process")
    public ResponseEntity<?> processMapping(@RequestBody MappingRequest request) {
        try {
            logger.info("Incoming request: mapping='{}', numberCount={}", request.mappingName(), 
                request.numbers() != null ? request.numbers().size() : 0);
            validateRequest(request);
            MappingResponse response = mappingService.processMapping(
                request.mappingName(), 
                request.numbers()
            );
            logger.info("Successfully processed mapping: {}", request.mappingName());
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            logger.warn("Bad request: {}", e.getMessage());
            return ResponseEntity.badRequest().body(new ErrorResponse("Bad Request", e.getMessage()));

        } catch (Exception e) {
            logger.error("Unexpected error processing mapping request", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/available")
    public ResponseEntity<List<String>> getAvailableMappings() {
        return ResponseEntity.ok(mappingService.getAvailableMappings());
    }

    private void validateRequest(MappingRequest request) {
        if (request.mappingName() == null || request.mappingName().trim().isEmpty()) {
            throw new IllegalArgumentException("Mapping name is required");
        }
        if (request.numbers() == null || request.numbers().isEmpty()) {
            throw new IllegalArgumentException("Numbers list cannot be empty");
        }
        for (Integer number : request.numbers()) {
            if (number == null || number < 1 || number > 20) {
                throw new IllegalArgumentException("Numbers must be in range 1-20");
            }
        }
    }
}

