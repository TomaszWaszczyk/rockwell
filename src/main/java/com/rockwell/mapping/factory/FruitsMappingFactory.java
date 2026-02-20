package com.rockwell.mapping.factory;

import java.util.HashMap;
import java.util.Map;

public class FruitsMappingFactory implements MappingFactory {
    @Override
    public Map<Integer, String> createMapping() {
        Map<Integer, String> mapping = new HashMap<>();
        mapping.put(1, "Apple");
        mapping.put(2, "Banana");
        mapping.put(3, "Cherry");
        mapping.put(4, "Date");
        mapping.put(5, "Elderberry");
        mapping.put(6, "Fig");
        mapping.put(7, "Grape");
        mapping.put(8, "Honeydew");
        mapping.put(9, "Kiwi");
        mapping.put(10, "Lemon");
        mapping.put(11, "Mango");
        mapping.put(12, "Nectarine");
        mapping.put(13, "Orange");
        mapping.put(14, "Papaya");
        mapping.put(15, "Quince");
        mapping.put(16, "Raspberry");
        mapping.put(17, "Strawberry");
        mapping.put(18, "Tangerine");
        mapping.put(19, "Ugli");
        mapping.put(20, "Watermelon");
        return mapping;
    }
}
