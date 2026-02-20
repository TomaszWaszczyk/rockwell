package com.rockwell.mapping.factory;

import java.util.HashMap;
import java.util.Map;

public class ColorsMappingFactory implements MappingFactory {
    @Override
    public Map<Integer, String> createMapping() {
        Map<Integer, String> mapping = new HashMap<>();
        mapping.put(1, "Red");
        mapping.put(2, "Blue");
        mapping.put(3, "Green");
        mapping.put(4, "Yellow");
        mapping.put(5, "Orange");
        mapping.put(6, "Purple");
        mapping.put(7, "Pink");
        mapping.put(8, "Brown");
        mapping.put(9, "Black");
        mapping.put(10, "White");
        mapping.put(11, "Gray");
        mapping.put(12, "Cyan");
        mapping.put(13, "Magenta");
        mapping.put(14, "Lime");
        mapping.put(15, "Navy");
        mapping.put(16, "Teal");
        mapping.put(17, "Maroon");
        mapping.put(18, "Olive");
        mapping.put(19, "Coral");
        mapping.put(20, "Gold");
        return mapping;
    }
}
