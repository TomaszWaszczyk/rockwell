package com.rockwell.mapping.dto;

import java.util.List;


public record MappingRequest(
        String mappingName,
        List<Integer> numbers
) {}
