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
        assertEquals("Animals", response.getMappingName());
        assertEquals(3, response.getResults().size());
    }

    @Test
    void testProcessMappingWithValidFurnitureMapping() {
        List<Integer> numbers = Arrays.asList(3, 6);
        MappingResponse response = mappingService.processMapping("Furniture", numbers);

        assertNotNull(response);
        assertEquals("Furniture", response.getMappingName());
        assertEquals(2, response.getResults().size());
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

        NumberMappingResponse result = response.getResults().get(0);
        assertEquals(6, result.getNumber());
        // 6 has divisors: 1, 2, 3, 6
        assertEquals(4, result.getDivisors().size());
        assertTrue(result.getDivisors().containsAll(Arrays.asList(1, 2, 3, 6)));
    }

    @Test
    void testProcessMappingMapsDivisorsCorrectly() {
        List<Integer> numbers = Arrays.asList(4);
        MappingResponse response = mappingService.processMapping("Animals", numbers);

        NumberMappingResponse result = response.getResults().get(0);
        // 4 has divisors: 1, 2, 4
        assertTrue(result.getDivisors().containsAll(Arrays.asList(1, 2, 4)));
        // Should have mapped words for these divisors
        assertFalse(result.getMappedWords().isEmpty());
    }

    @Test
    void testProcessMappingWithSingleNumber() {
        List<Integer> numbers = Arrays.asList(1);
        MappingResponse response = mappingService.processMapping("Animals", numbers);

        assertEquals(1, response.getResults().size());
        NumberMappingResponse result = response.getResults().get(0);
        assertEquals(1, result.getNumber());
        assertTrue(result.getDivisors().contains(1));
    }

    @Test
    void testProcessMappingWithMultipleNumbers() {
        List<Integer> numbers = Arrays.asList(2, 3, 5);
        MappingResponse response = mappingService.processMapping("Animals", numbers);

        assertEquals(3, response.getResults().size());
        assertEquals(2, response.getResults().get(0).getNumber());
        assertEquals(3, response.getResults().get(1).getNumber());
        assertEquals(5, response.getResults().get(2).getNumber());
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
        assertTrue(mappings.contains("Vehicles"));
        assertTrue(mappings.contains("Sports"));
        assertTrue(mappings.contains("Instruments"));
        assertTrue(mappings.contains("Countries"));
        assertTrue(mappings.contains("Professions"));
        assertTrue(mappings.contains("Planets"));
    }

    @Test
    void testGetAvailableMappingsReturnsAllMappings() {
        List<String> mappings = mappingService.getAvailableMappings();
        assertEquals(10, mappings.size());
    }

    @Test
    void testProcessMappingWithDivisorOne() {
        List<Integer> numbers = Arrays.asList(1);
        MappingResponse response = mappingService.processMapping("Animals", numbers);

        NumberMappingResponse result = response.getResults().get(0);
        assertEquals(1, result.getDivisors().size());
        assertEquals(1, result.getDivisors().get(0).intValue());
    }

    @Test
    void testProcessMappingWithPrimeNumber() {
        List<Integer> numbers = Arrays.asList(5);
        MappingResponse response = mappingService.processMapping("Animals", numbers);

        NumberMappingResponse result = response.getResults().get(0);
        assertEquals(2, result.getDivisors().size());
        assertTrue(result.getDivisors().containsAll(Arrays.asList(1, 5)));
    }

    @Test
    void testProcessMappingWithCompositeNumber() {
        List<Integer> numbers = Arrays.asList(12);
        MappingResponse response = mappingService.processMapping("Animals", numbers);

        NumberMappingResponse result = response.getResults().get(0);
        // 12 has divisors: 1, 2, 3, 4, 6, 12
        assertEquals(6, result.getDivisors().size());
    }

    @Test
    void testProcessMappingAllMappingsWork() {
        List<String> mappings = mappingService.getAvailableMappings();
        List<Integer> testNumbers = Arrays.asList(2, 4, 6);

        for (String mappingName : mappings) {
            MappingResponse response = mappingService.processMapping(mappingName, testNumbers);
            assertNotNull(response);
            assertEquals(mappingName, response.getMappingName());
            assertEquals(3, response.getResults().size());
        }
    }
}
