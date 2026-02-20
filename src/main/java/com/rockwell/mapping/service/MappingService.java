package com.rockwell.mapping.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rockwell.mapping.dto.MappingResponse;
import com.rockwell.mapping.dto.NumberMappingResponse;
import com.rockwell.mapping.factory.AnimalsMappingFactory;
import com.rockwell.mapping.factory.ColorsMappingFactory;
import com.rockwell.mapping.factory.CountriesMappingFactory;
import com.rockwell.mapping.factory.FruitsMappingFactory;
import com.rockwell.mapping.factory.FurnitureMappingFactory;


@Service
public class MappingService {

    private static final Logger logger = LoggerFactory.getLogger(MappingService.class);
    private final Map<String, Map<Integer, String>> mappings;

    public MappingService() {
        this.mappings = initializeMappings();
    }

    public MappingResponse processMapping(String mappingName, List<Integer> numbers) {
        logger.info("Processing request for mapping: '{}'" , mappingName);
        Map<Integer, String> mapping = mappings.get(mappingName);
        if (mapping == null) {
            logger.warn("Mapping '{}' not found", mappingName);
            throw new IllegalArgumentException("Mapping '" + mappingName + "' not found. Available mappings: " + 
                String.join(", ", mappings.keySet()));
        }

        List<NumberMappingResponse> results = numbers.stream()
            .map(number -> {
                List<Integer> divisors = findDivisors(number);
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
        return new ArrayList<>(mappings.keySet());
    }

    private List<Integer> findDivisors(int number) {
        return IntStream.rangeClosed(1, number)
            .filter(i -> number % i == 0)
            .boxed()
            .collect(Collectors.toList());
    }

    private Map<String, Map<Integer, String>> initializeMappings() {
        return Map.of(
            "Animals", new AnimalsMappingFactory().createMapping(),
            "Furniture", new FurnitureMappingFactory().createMapping(),
            "Colors", new ColorsMappingFactory().createMapping(),
            "Fruits", new FruitsMappingFactory().createMapping(),
            "Countries", new CountriesMappingFactory().createMapping()
        );
    }
}
