package com.rockwell.mapping.dto;

import java.util.List;


public record NumberMappingResponse(
        Integer number,
        List<Integer> divisors,
        List<String> mappedWords
) {}
