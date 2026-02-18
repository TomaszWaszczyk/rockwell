package com.rockwell.mapping.dto;

import java.util.List;

public class MappingResponse {
    private String mappingName;
    private List<NumberMappingResponse> results;

    public MappingResponse() {
    }

    public MappingResponse(String mappingName, List<NumberMappingResponse> results) {
        this.mappingName = mappingName;
        this.results = results;
    }

    public String getMappingName() {
        return mappingName;
    }

    public void setMappingName(String mappingName) {
        this.mappingName = mappingName;
    }

    public List<NumberMappingResponse> getResults() {
        return results;
    }

    public void setResults(List<NumberMappingResponse> results) {
        this.results = results;
    }
}

