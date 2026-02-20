package com.rockwell.mapping.factory;

import java.util.HashMap;
import java.util.Map;

public class CountriesMappingFactory implements MappingFactory {
    @Override
    public Map<Integer, String> createMapping() {
        Map<Integer, String> mapping = new HashMap<>();
        mapping.put(1, "USA");
        mapping.put(2, "Canada");
        mapping.put(3, "Mexico");
        mapping.put(4, "Brazil");
        mapping.put(5, "Argentina");
        mapping.put(6, "UK");
        mapping.put(7, "France");
        mapping.put(8, "Germany");
        mapping.put(9, "Italy");
        mapping.put(10, "Spain");
        mapping.put(11, "Japan");
        mapping.put(12, "China");
        mapping.put(13, "India");
        mapping.put(14, "Australia");
        mapping.put(15, "Russia");
        mapping.put(16, "Egypt");
        mapping.put(17, "SouthAfrica");
        mapping.put(18, "Nigeria");
        mapping.put(19, "Kenya");
        mapping.put(20, "Morocco");
        return mapping;
    }
}
