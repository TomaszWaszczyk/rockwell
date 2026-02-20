package com.rockwell.mapping.registry;

import java.util.Map;

import com.rockwell.mapping.factory.AnimalsMappingFactory;
import com.rockwell.mapping.factory.ColorsMappingFactory;
import com.rockwell.mapping.factory.CountriesMappingFactory;
import com.rockwell.mapping.factory.FruitsMappingFactory;
import com.rockwell.mapping.factory.FurnitureMappingFactory;

public class MappingRegistry {
    private final Map<String, Map<Integer, String>> mappings;

    public MappingRegistry() {
        this.mappings = initializeMappings();
    }

    private Map<String, Map<Integer, String>> initializeMappings() {
        return Map.of(
            "Animals", new AnimalsMappingFactory().createMapping(),
            "Furniture", new FurnitureMappingFactory().createMapping(),
            "Colors", new ColorsMappingFactory().createMapping(),
            "Fruits", new FruitsMappingFactory().createMapping(),
            "Countries", new CountriesMappingFactory().createMapping()
        );
    }

    public Map<Integer, String> getMapping(String name) {
        return mappings.get(name);
    }

    public Map<String, Map<Integer, String>> getAllMappings() {
        return mappings;
    }
}
