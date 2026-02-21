package com.rockwell.mapping.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DivisorService {
    public List<Integer> findDivisors(int number) {
        return IntStream.rangeClosed(1, number)
                .filter(i -> number % i == 0)
                .boxed()
                .collect(Collectors.toList());
    }
}
