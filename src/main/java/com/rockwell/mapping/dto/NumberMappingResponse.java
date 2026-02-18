package com.rockwell.mapping.dto;

import java.util.List;

public class NumberMappingResponse {
    private Integer number;
    private List<Integer> divisors;
    private List<String> mappedWords;

    public NumberMappingResponse() {
    }

    public NumberMappingResponse(Integer number, List<Integer> divisors, List<String> mappedWords) {
        this.number = number;
        this.divisors = divisors;
        this.mappedWords = mappedWords;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<Integer> getDivisors() {
        return divisors;
    }

    public void setDivisors(List<Integer> divisors) {
        this.divisors = divisors;
    }

    public List<String> getMappedWords() {
        return mappedWords;
    }

    public void setMappedWords(List<String> mappedWords) {
        this.mappedWords = mappedWords;
    }
}

