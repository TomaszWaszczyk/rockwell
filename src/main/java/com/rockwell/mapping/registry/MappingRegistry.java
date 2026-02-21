package com.rockwell.mapping.registry;

import java.util.Map;

import com.rockwell.mapping.factory.AnimalsMappingFactory;
import com.rockwell.mapping.factory.ColorsMappingFactory;
import com.rockwell.mapping.factory.CountriesMappingFactory;
import com.rockwell.mapping.factory.FruitsMappingFactory;
import com.rockwell.mapping.factory.FurnitureMappingFactory;

public class MappingRegistry {
    private final Map<String, MappingData> mappings;

    public MappingRegistry() {
        this.mappings = initializeMappings();
    }

    private Map<String, MappingData> initializeMappings() {
        return Map.of(
            "Animals", new MappingData(new AnimalsMappingFactory().createMapping()),
            "Furniture", new MappingData(new FurnitureMappingFactory().createMapping()),
            "Colors", new MappingData(new ColorsMappingFactory().createMapping()),
            "Fruits", new MappingData(new FruitsMappingFactory().createMapping()),
            "Countries", new MappingData(new CountriesMappingFactory().createMapping())
        );
    }

    public MappingData getMapping(String name) {
        return mappings.get(name);
    }

    public Map<String, MappingData> getAllMappings() {
        return mappings;
    }
}
