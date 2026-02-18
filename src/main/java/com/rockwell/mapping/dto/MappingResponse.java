package com.rockwell.mapping.dto;

import java.util.List;


public record MappingResponse(
        String mappingName,
        List<NumberMappingResponse> results
) {}
