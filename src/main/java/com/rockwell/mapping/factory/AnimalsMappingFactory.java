package com.rockwell.mapping.factory;

import java.util.HashMap;
import java.util.Map;

public class AnimalsMappingFactory implements MappingFactory {
    @Override
    public Map<Integer, String> createMapping() {
        Map<Integer, String> mapping = new HashMap<>();
        mapping.put(1, "Mouse");
        mapping.put(2, "Cat");
        mapping.put(3, "Dog");
        mapping.put(4, "Elephant");
        mapping.put(5, "Lion");
        mapping.put(6, "Tiger");
        mapping.put(7, "Bear");
        mapping.put(8, "Wolf");
        mapping.put(9, "Fox");
        mapping.put(10, "Deer");
        mapping.put(11, "Rabbit");
        mapping.put(12, "Horse");
        mapping.put(13, "Cow");
        mapping.put(14, "Pig");
        mapping.put(15, "Sheep");
        mapping.put(16, "Goat");
        mapping.put(17, "Duck");
        mapping.put(18, "Chicken");
        mapping.put(19, "Goose");
        mapping.put(20, "Swan");
        return mapping;
    }
}
