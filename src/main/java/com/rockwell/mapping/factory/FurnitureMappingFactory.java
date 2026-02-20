package com.rockwell.mapping.factory;

import java.util.HashMap;
import java.util.Map;

public class FurnitureMappingFactory implements MappingFactory {
    @Override
    public Map<Integer, String> createMapping() {
        Map<Integer, String> mapping = new HashMap<>();
        mapping.put(1, "Chair");
        mapping.put(2, "Table");
        mapping.put(3, "Cabinet");
        mapping.put(4, "Bed");
        mapping.put(5, "Sofa");
        mapping.put(6, "Desk");
        mapping.put(7, "Shelf");
        mapping.put(8, "Dresser");
        mapping.put(9, "Stool");
        mapping.put(10, "Bench");
        mapping.put(11, "Ottoman");
        mapping.put(12, "Armchair");
        mapping.put(13, "Bookcase");
        mapping.put(14, "Wardrobe");
        mapping.put(15, "Sideboard");
        mapping.put(16, "Console");
        mapping.put(17, "Credenza");
        mapping.put(18, "Buffet");
        mapping.put(19, "Hutch");
        mapping.put(20, "Chest");
        return mapping;
    }
}
