package com.rockwell.mapping.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rockwell.mapping.dto.MappingResponse;
import com.rockwell.mapping.dto.NumberMappingResponse;
import com.rockwell.mapping.registry.MappingRegistry;
import com.rockwell.mapping.util.DivisorService;


@Service
public class MappingService {

    private static final Logger logger = LoggerFactory.getLogger(MappingService.class);
    private final MappingRegistry mappingRegistry = new MappingRegistry();
    private final DivisorService divisorService = new DivisorService();

    public MappingResponse processMapping(String mappingName, List<Integer> numbers) {
        logger.info("Processing request for mapping: '{}'" , mappingName);
        Map<Integer, String> mapping = mappingRegistry.getMapping(mappingName);
        if (mapping == null) {
            logger.warn("Mapping '{}' not found", mappingName);
            throw new IllegalArgumentException("Mapping '" + mappingName + "' not found. Available mappings: " + 
                String.join(", ", mappingRegistry.getAllMappings().keySet()));
        }

        List<NumberMappingResponse> results = numbers.stream()
            .map(number -> {
                List<Integer> divisors = divisorService.findDivisors(number);
                List<String> mappedWords = divisors.stream()
                    .map(divisor -> mapping.get(divisor))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
                return new NumberMappingResponse(number, divisors, mappedWords);
            })
            .collect(Collectors.toList());

        return new MappingResponse(mappingName, results);
    }

    public List<String> getAvailableMappings() {
        return new ArrayList<>(mappingRegistry.getAllMappings().keySet());
    }
}
