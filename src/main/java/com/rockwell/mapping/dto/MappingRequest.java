package com.rockwell.mapping.dto;

import java.util.List;

public class MappingRequest {
    private String mappingName;
    private List<Integer> numbers;

    public MappingRequest() {
    }

    public MappingRequest(String mappingName, List<Integer> numbers) {
        this.mappingName = mappingName;
        this.numbers = numbers;
    }

    public String getMappingName() {
        return mappingName;
    }

    public void setMappingName(String mappingName) {
        this.mappingName = mappingName;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }
}
