package com.rockwell.mapping.service;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.rockwell.mapping.dto.MappingResponse;
import com.rockwell.mapping.dto.NumberMappingResponse;

class MappingServiceTest {

    private MappingService mappingService;

    @BeforeEach
    void setUp() {
        mappingService = new MappingService();
    }

    @Test
    void testProcessMappingWithValidAnimalsMapping() {
        List<Integer> numbers = Arrays.asList(1, 2, 4);
        MappingResponse response = mappingService.processMapping("Animals", numbers);

        assertNotNull(response);
        assertEquals("Animals", response.mappingName());
        assertEquals(3, response.results().size());
    }

    @Test
    void testProcessMappingWithValidFurnitureMapping() {
        List<Integer> numbers = Arrays.asList(3, 6);
        MappingResponse response = mappingService.processMapping("Furniture", numbers);

        assertNotNull(response);
        assertEquals("Furniture", response.mappingName());
        assertEquals(2, response.results().size());
    }

    @Test
    void testProcessMappingWithInvalidMappingName() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> mappingService.processMapping("InvalidMapping", numbers)
        );
        
        assertTrue(exception.getMessage().contains("not found"));
        assertTrue(exception.getMessage().contains("Available mappings"));
    }

    @Test
    void testProcessMappingFindsDivisorsCorrectly() {
        List<Integer> numbers = Arrays.asList(6);
        MappingResponse response = mappingService.processMapping("Animals", numbers);

        NumberMappingResponse result = response.results().get(0);
        assertEquals(6, result.number());
        // 6 has divisors: 1, 2, 3, 6
        assertEquals(4, result.divisors().size());
        assertTrue(result.divisors().containsAll(Arrays.asList(1, 2, 3, 6)));
    }

    @Test
    void testProcessMappingMapsDivisorsCorrectly() {
        List<Integer> numbers = Arrays.asList(4);
        MappingResponse response = mappingService.processMapping("Animals", numbers);

        NumberMappingResponse result = response.results().get(0);
        // 4 has divisors: 1, 2, 4
        assertTrue(result.divisors().containsAll(Arrays.asList(1, 2, 4)));
        // Should have mapped words for these divisors
        assertFalse(result.mappedWords().isEmpty());
    }

    @Test
    void testProcessMappingWithSingleNumber() {
        List<Integer> numbers = Arrays.asList(1);
        MappingResponse response = mappingService.processMapping("Animals", numbers);

        assertEquals(1, response.results().size());
        NumberMappingResponse result = response.results().get(0);
        assertEquals(1, result.number());
        assertTrue(result.divisors().contains(1));
    }

    @Test
    void testProcessMappingWithMultipleNumbers() {
        List<Integer> numbers = Arrays.asList(2, 3, 5);
        MappingResponse response = mappingService.processMapping("Animals", numbers);

        assertEquals(3, response.results().size());
        assertEquals(2, response.results().get(0).number());
        assertEquals(3, response.results().get(1).number());
        assertEquals(5, response.results().get(2).number());
    }

    @Test
    void testGetAvailableMappings() {
        List<String> mappings = mappingService.getAvailableMappings();

        assertNotNull(mappings);
        assertFalse(mappings.isEmpty());
        assertTrue(mappings.contains("Animals"));
        assertTrue(mappings.contains("Furniture"));
        assertTrue(mappings.contains("Colors"));
        assertTrue(mappings.contains("Fruits"));
        assertTrue(mappings.contains("Countries"));
    }

    @Test
    void testGetAvailableMappingsReturnsAllMappings() {
        List<String> mappings = mappingService.getAvailableMappings();
        assertEquals(5, mappings.size());
    }

    @Test
    void testProcessMappingWithDivisorOne() {
        List<Integer> numbers = Arrays.asList(1);
        MappingResponse response = mappingService.processMapping("Animals", numbers);

        NumberMappingResponse result = response.results().get(0);
        assertEquals(1, result.divisors().size());
        assertEquals(1, result.divisors().get(0).intValue());
    }

    @Test
    void testProcessMappingWithPrimeNumber() {
        List<Integer> numbers = Arrays.asList(5);
        MappingResponse response = mappingService.processMapping("Animals", numbers);

        NumberMappingResponse result = response.results().get(0);
        assertEquals(2, result.divisors().size());
        assertTrue(result.divisors().containsAll(Arrays.asList(1, 5)));
    }

    @Test
    void testProcessMappingWithCompositeNumber() {
        List<Integer> numbers = Arrays.asList(12);
        MappingResponse response = mappingService.processMapping("Animals", numbers);

        NumberMappingResponse result = response.results().get(0);
        // 12 has divisors: 1, 2, 3, 4, 6, 12
        assertEquals(6, result.divisors().size());
    }

    @Test
    void testProcessMappingAllMappingsWork() {
        List<String> mappings = mappingService.getAvailableMappings();
        List<Integer> testNumbers = Arrays.asList(2, 4, 6);

        for (String mappingName : mappings) {
            MappingResponse response = mappingService.processMapping(mappingName, testNumbers);
            assertNotNull(response);
            assertEquals(mappingName, response.mappingName());
            assertEquals(3, response.results().size());
        }
    }
}
