package com.rockwell.mapping.controller;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rockwell.mapping.dto.MappingRequest;
import com.rockwell.mapping.dto.MappingResponse;
import com.rockwell.mapping.dto.NumberMappingResponse;
import com.rockwell.mapping.service.MappingService;

class MappingControllerTest {

    private MappingController mappingController;
    private MappingService mappingService;

    @BeforeEach
    void setUp() {
        mappingService = new MappingService();
        mappingController = new MappingController(mappingService);
    }

    @Test
    void testProcessMappingWithValidRequest() {
        MappingRequest request = new MappingRequest("Animals", Arrays.asList(1, 2, 4));
        ResponseEntity<MappingResponse> response = mappingController.processMapping(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Animals", response.getBody().mappingName());
        assertEquals(3, response.getBody().results().size());
    }

    @Test
    void testProcessMappingWithInvalidMappingName() {
        MappingRequest request = new MappingRequest("InvalidMapping", Arrays.asList(1, 2));
        ResponseEntity<MappingResponse> response = mappingController.processMapping(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testProcessMappingWithEmptyMappingName() {
        MappingRequest request = new MappingRequest("", Arrays.asList(1, 2));
        ResponseEntity<MappingResponse> response = mappingController.processMapping(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testProcessMappingWithNullMappingName() {
        MappingRequest request = new MappingRequest(null, Arrays.asList(1, 2));
        ResponseEntity<MappingResponse> response = mappingController.processMapping(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testProcessMappingWithEmptyNumbers() {
        MappingRequest request = new MappingRequest("Animals", Arrays.asList());
        ResponseEntity<MappingResponse> response = mappingController.processMapping(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testProcessMappingWithNullNumbers() {
        MappingRequest request = new MappingRequest("Animals", null);
        ResponseEntity<MappingResponse> response = mappingController.processMapping(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testProcessMappingWithNumberOutOfRange() {
        MappingRequest request = new MappingRequest("Animals", Arrays.asList(1, 21));
        ResponseEntity<MappingResponse> response = mappingController.processMapping(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testProcessMappingWithNumberZero() {
        MappingRequest request = new MappingRequest("Animals", Arrays.asList(0, 1));
        ResponseEntity<MappingResponse> response = mappingController.processMapping(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testProcessMappingWithNullNumberInList() {
        MappingRequest request = new MappingRequest("Animals", Arrays.asList(1, null, 3));
        ResponseEntity<MappingResponse> response = mappingController.processMapping(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testGetAvailableMappings() {
        ResponseEntity<List<String>> response = mappingController.getAvailableMappings();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
        assertEquals(10, response.getBody().size());
        assertTrue(response.getBody().contains("Animals"));
        assertTrue(response.getBody().contains("Furniture"));
        assertTrue(response.getBody().contains("Colors"));
        assertTrue(response.getBody().contains("Fruits"));
        assertTrue(response.getBody().contains("Vehicles"));
        assertTrue(response.getBody().contains("Sports"));
        assertTrue(response.getBody().contains("Instruments"));
        assertTrue(response.getBody().contains("Countries"));
        assertTrue(response.getBody().contains("Professions"));
        assertTrue(response.getBody().contains("Planets"));
    }

    @Test
    void testProcessMappingResponseStructure() {
        MappingRequest request = new MappingRequest("Animals", Arrays.asList(2));
        ResponseEntity<MappingResponse> response = mappingController.processMapping(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().mappingName());
        assertNotNull(response.getBody().results());
        assertEquals(1, response.getBody().results().size());
        
        NumberMappingResponse result = response.getBody().results().get(0);
        assertEquals(2, result.number());
        assertNotNull(result.divisors());
        assertNotNull(result.mappedWords());
    }

    @Test
    void testProcessMappingWithValidNumberRange() {
        MappingRequest request = new MappingRequest("Furniture", Arrays.asList(1, 10, 20));
        ResponseEntity<MappingResponse> response = mappingController.processMapping(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Furniture", response.getBody().mappingName());
        assertEquals(3, response.getBody().results().size());
    }

    @Test
    void testProcessMappingWithDifferentMappingTypes() {
        List<String> mappingNames = Arrays.asList("Animals", "Furniture", "Colors", "Fruits");
        
        for (String mappingName : mappingNames) {
            MappingRequest request = new MappingRequest(mappingName, Arrays.asList(2, 4));
            ResponseEntity<MappingResponse> response = mappingController.processMapping(request);
            
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(mappingName, response.getBody().mappingName());
        }
    }

    @Test
    void testProcessMappingErrorHandling() {
        MappingRequest request = new MappingRequest("Animals", Arrays.asList(1));
        ResponseEntity<MappingResponse> response = mappingController.processMapping(request);
        
        assertNotNull(response);
        assertTrue(response.getStatusCode().is2xxSuccessful() || response.getStatusCode().is4xxClientError());
    }
}
