package com.rockwell.mapping.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rockwell.mapping.dto.MappingResponse;
import com.rockwell.mapping.dto.NumberMappingResponse;

@Service
public class MappingService {

    private static final Logger logger = LoggerFactory.getLogger(MappingService.class);
    private final Map<String, Map<Integer, String>> mappings;

    public MappingService() {
        this.mappings = initializeMappings();
    }

    public MappingResponse processMapping(String mappingName, List<Integer> numbers) {
        logger.info("Processing request for mapping: '{}'" , mappingName);
        Map<Integer, String> mapping = mappings.get(mappingName);
        if (mapping == null) {
            logger.warn("Mapping '{}' not found", mappingName);
            throw new IllegalArgumentException("Mapping '" + mappingName + "' not found. Available mappings: " + 
                String.join(", ", mappings.keySet()));
        }

        List<NumberMappingResponse> results = numbers.stream()
            .map(number -> {
                List<Integer> divisors = findDivisors(number);
                List<String> mappedWords = divisors.stream()
                    .map(divisor -> mapping.get(divisor))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
                return new NumberMappingResponse(number, divisors, mappedWords);
            })
            .collect(Collectors.toList());

        return new MappingResponse(mappingName, results);
    }

    public List<String> getAvailableMappings() {
        return new ArrayList<>(mappings.keySet());
    }

    private List<Integer> findDivisors(int number) {
        return IntStream.rangeClosed(1, number)
            .filter(i -> number % i == 0)
            .boxed()
            .collect(Collectors.toList());
    }


    private Map<String, Map<Integer, String>> initializeMappings() {
    return Map.copyOf(Map.of(
        "Animals", Map.copyOf(createAnimalsMapping()),
        "Furniture", Map.copyOf(createFurnitureMapping()),
        "Colors", Map.copyOf(createColorsMapping()),
        "Fruits", Map.copyOf(createFruitsMapping()),
        "Vehicles", Map.copyOf(createVehiclesMapping()),
        "Sports", Map.copyOf(createSportsMapping()),
        "Instruments", Map.copyOf(createInstrumentsMapping()),
        "Countries", Map.copyOf(createCountriesMapping()),
        "Professions", Map.copyOf(createProfessionsMapping()),
        "Planets", Map.copyOf(createPlanetsMapping())
    ));
    }


    // private Map<String, Map<Integer, String>> initializeMappings() {
    //     Map<String, Map<Integer, String>> allMappings = new HashMap<>();

    //     // Mapping 1: Animals
    //     allMappings.put("Animals", Map.copyOf(createAnimalsMapping()));

    //     // Mapping 2: Furniture
    //     allMappings.put("Furniture", Map.copyOf(createFurnitureMapping()));

    //     // Mapping 3: Colors
    //     allMappings.put("Colors", Map.copyOf(createColorsMapping()));

    //     // Mapping 4: Fruits
    //     allMappings.put("Fruits", Map.copyOf(createFruitsMapping()));

    //     // Mapping 5: Vehicles
    //     allMappings.put("Vehicles", Map.copyOf(createVehiclesMapping()));

    //     // Mapping 6: Sports
    //     allMappings.put("Sports", Map.copyOf(createSportsMapping()));

    //     // Mapping 7: Instruments
    //     allMappings.put("Instruments", Map.copyOf(createInstrumentsMapping()));

    //     // Mapping 8: Countries
    //     allMappings.put("Countries", Map.copyOf(createCountriesMapping()));

    //     // Mapping 9: Professions
    //     allMappings.put("Professions", Map.copyOf(createProfessionsMapping()));

    //     // Mapping 10: Planets
    //     allMappings.put("Planets", Map.copyOf(createPlanetsMapping()));

    //     return Map.copyOf(allMappings);
    // }

    private Map<Integer, String> createAnimalsMapping() {
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

    private Map<Integer, String> createFurnitureMapping() {
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

    private Map<Integer, String> createColorsMapping() {
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

    private Map<Integer, String> createFruitsMapping() {
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

    private Map<Integer, String> createVehiclesMapping() {
        Map<Integer, String> mapping = new HashMap<>();
        mapping.put(1, "Car");
        mapping.put(2, "Truck");
        mapping.put(3, "Bus");
        mapping.put(4, "Motorcycle");
        mapping.put(5, "Bicycle");
        mapping.put(6, "Train");
        mapping.put(7, "Plane");
        mapping.put(8, "Boat");
        mapping.put(9, "Ship");
        mapping.put(10, "Helicopter");
        mapping.put(11, "Subway");
        mapping.put(12, "Tram");
        mapping.put(13, "Taxi");
        mapping.put(14, "Van");
        mapping.put(15, "SUV");
        mapping.put(16, "Scooter");
        mapping.put(17, "Skateboard");
        mapping.put(18, "Segway");
        mapping.put(19, "GolfCart");
        mapping.put(20, "Tractor");
        return mapping;
    }

    private Map<Integer, String> createSportsMapping() {
        Map<Integer, String> mapping = new HashMap<>();
        mapping.put(1, "Soccer");
        mapping.put(2, "Basketball");
        mapping.put(3, "Tennis");
        mapping.put(4, "Baseball");
        mapping.put(5, "Football");
        mapping.put(6, "Volleyball");
        mapping.put(7, "Swimming");
        mapping.put(8, "Running");
        mapping.put(9, "Cycling");
        mapping.put(10, "Golf");
        mapping.put(11, "Hockey");
        mapping.put(12, "Rugby");
        mapping.put(13, "Cricket");
        mapping.put(14, "Boxing");
        mapping.put(15, "Wrestling");
        mapping.put(16, "Skiing");
        mapping.put(17, "Surfing");
        mapping.put(18, "Climbing");
        mapping.put(19, "Diving");
        mapping.put(20, "Archery");
        return mapping;
    }

    private Map<Integer, String> createInstrumentsMapping() {
        Map<Integer, String> mapping = new HashMap<>();
        mapping.put(1, "Piano");
        mapping.put(2, "Guitar");
        mapping.put(3, "Violin");
        mapping.put(4, "Drums");
        mapping.put(5, "Flute");
        mapping.put(6, "Trumpet");
        mapping.put(7, "Saxophone");
        mapping.put(8, "Clarinet");
        mapping.put(9, "Cello");
        mapping.put(10, "Harp");
        mapping.put(11, "Banjo");
        mapping.put(12, "Mandolin");
        mapping.put(13, "Oboe");
        mapping.put(14, "Bassoon");
        mapping.put(15, "Trombone");
        mapping.put(16, "Tuba");
        mapping.put(17, "Harmonica");
        mapping.put(18, "Accordion");
        mapping.put(19, "Organ");
        mapping.put(20, "Xylophone");
        return mapping;
    }

    private Map<Integer, String> createCountriesMapping() {
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

    private Map<Integer, String> createProfessionsMapping() {
        Map<Integer, String> mapping = new HashMap<>();
        mapping.put(1, "Doctor");
        mapping.put(2, "Teacher");
        mapping.put(3, "Engineer");
        mapping.put(4, "Lawyer");
        mapping.put(5, "Nurse");
        mapping.put(6, "Chef");
        mapping.put(7, "Artist");
        mapping.put(8, "Musician");
        mapping.put(9, "Writer");
        mapping.put(10, "Pilot");
        mapping.put(11, "Architect");
        mapping.put(12, "Scientist");
        mapping.put(13, "Designer");
        mapping.put(14, "Accountant");
        mapping.put(15, "Manager");
        mapping.put(16, "Consultant");
        mapping.put(17, "Analyst");
        mapping.put(18, "Developer");
        mapping.put(19, "Director");
        mapping.put(20, "Executive");
        return mapping;
    }

    private Map<Integer, String> createPlanetsMapping() {
        Map<Integer, String> mapping = new HashMap<>();
        mapping.put(1, "Mercury");
        mapping.put(2, "Venus");
        mapping.put(3, "Earth");
        mapping.put(4, "Mars");
        mapping.put(5, "Jupiter");
        mapping.put(6, "Saturn");
        mapping.put(7, "Uranus");
        mapping.put(8, "Neptune");
        mapping.put(9, "Pluto");
        mapping.put(10, "Ceres");
        mapping.put(11, "Eris");
        mapping.put(12, "Haumea");
        mapping.put(13, "Makemake");
        mapping.put(14, "Ganymede");
        mapping.put(15, "Titan");
        mapping.put(16, "Callisto");
        mapping.put(17, "Io");
        mapping.put(18, "Europa");
        mapping.put(19, "Triton");
        mapping.put(20, "Charon");
        return mapping;
    }
}

