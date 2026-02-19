[![Run in Postman](https://run.pstmn.io/button-24.svg)](https://app.getpostman.com/run-collection/5091521-f2beda7d-f3d9-5aac-f70f-d694ef32c174)


# Rockwell Mapping Application

A RESTful Spring Boot application that accepts a group of numbers and a mapping name, finds divisors for each number, and maps each divisor to a word using predefined mappings.

## Features

- Accepts a group of numbers (1-20) and a mapping name
- Calculates divisors for each number
- Maps divisors to words using 10 predefined mappings
- Returns numbers with their corresponding divisors and mapped words
- Extensible architecture for adding new mappings

## Available Mappings

The application provides 10 different mapping setups:

1. **Animals** - Mouse, Cat, Dog, Elephant, Lion, etc.
2. **Furniture** - Chair, Table, Cabinet, Bed, Sofa, etc.
3. **Colors** - Red, Blue, Green, Yellow, Orange, etc.
4. **Fruits** - Apple, Banana, Cherry, Date, Elderberry, etc.
5. **Vehicles** - Car, Truck, Bus, Motorcycle, Bicycle, etc.
6. **Sports** - Soccer, Basketball, Tennis, Baseball, Football, etc.
7. **Instruments** - Piano, Guitar, Violin, Drums, Flute, etc.
8. **Countries** - USA, Canada, Mexico, Brazil, Argentina, etc.
9. **Professions** - Doctor, Teacher, Engineer, Lawyer, Nurse, etc.
10. **Planets** - Mercury, Venus, Earth, Mars, Jupiter, etc.

## API Endpoints

### Process Mapping

**POST** `/api/mapping/process`

Request body:
```json
{
  "mappingName": "Animals",
  "numbers": [1, 2, 4]
}
```

Response:
```json
{
  "mappingName": "Animals",
  "results": [
    {
      "number": 1,
      "divisors": [1],
      "mappedWords": ["Mouse"]
    },
    {
      "number": 2,
      "divisors": [1, 2],
      "mappedWords": ["Mouse", "Cat"]
    },
    {
      "number": 4,
      "divisors": [1, 2, 4],
      "mappedWords": ["Mouse", "Cat", "Elephant"]
    }
  ]
}
```

### Get Available Mappings

**GET** `/api/mapping/available`

Response:
```json
["Animals", "Furniture", "Colors", "Fruits", "Vehicles", "Sports", "Instruments", "Countries", "Professions", "Planets"]
```

## Prerequisites

- Java 25 (as specified in pom.xml - learned that Railway.com supports the highest version of Java 21)
- Maven 3.6+ (or use the included Maven Wrapper)

## Building the Application

### Using Maven Wrapper (Recommended)

**Linux/Mac:**
```bash
./mvnw clean package
```

**Windows:**
```bash
mvnw.cmd clean package
```

### Using Maven (if installed)
```bash
mvn clean package
```

This will create a WAR file in the `target` directory: `mapping-0.0.1-SNAPSHOT.war`

## Running the Application

### Option 1: Run as Spring Boot Application

**Using Maven Wrapper:**
```bash
./mvnw spring-boot:run
```

**Using Maven:**
```bash
mvn spring-boot:run
```

### Option 2: Run the WAR File

After building, you can deploy the WAR file to a servlet container (e.g., Tomcat) or run it directly:

```bash
java -jar target/mapping-0.0.1-SNAPSHOT.war
```

The application will start on port 8080 by default.

## Configuration

The application uses `application.properties` for configuration. Default settings:
- Application name: `mapping`
- Server port: `8080` (Spring Boot default)

To change the port, add to `src/main/resources/application.properties`:
```properties
server.port=8080
```

## Testing the Application

### Using cURL

**Get available mappings:**
```bash
curl http://localhost:8080/api/mapping/available
```

**Process a mapping:**
```bash
curl -X POST http://localhost:8080/api/mapping/process \
  -H "Content-Type: application/json" \
  -d '{
    "mappingName": "Animals",
    "numbers": [1, 2, 4]
  }'
```

### Example Request/Response

**Request:**
```bash
curl -X POST http://localhost:8080/api/mapping/process \
  -H "Content-Type: application/json" \
  -d '{
    "mappingName": "Furniture",
    "numbers": [1, 2, 4]
  }'
```

**Response:**
```json
{
  "mappingName": "Furniture",
  "results": [
    {
      "number": 1,
      "divisors": [1],
      "mappedWords": ["Chair"]
    },
    {
      "number": 2,
      "divisors": [1, 2],
      "mappedWords": ["Chair", "Table"]
    },
    {
      "number": 4,
      "divisors": [1, 2, 4],
      "mappedWords": ["Chair", "Table", "Bed"]
    }
  ]
}
```

## Deployment

### Deploy to Other Platforms

The application is packaged as a WAR file, making it compatible with:
- Apache Tomcat
- Jetty
- Any Java servlet container
- Cloud platforms (AWS, Azure, GCP, Heroku, etc.)

## Extensibility

To add a new mapping:

1. Add a new method in `MappingService` to create the mapping (e.g., `createNewCategoryMapping()`)
2. Call this method in the `initializeMappings()` method
3. Ensure all numbers from 1-20 have unique words assigned

Example:
```java
private Map<Integer, String> createNewCategoryMapping() {
    Map<Integer, String> mapping = new HashMap<>();
    mapping.put(1, "Word1");
    mapping.put(2, "Word2");
    // ... up to 20
    return mapping;
}
```

## Error Handling

The application includes global exception handling:
- Invalid mapping names return 400 Bad Request
- Invalid number ranges (outside 1-20) return 400 Bad Request
- Missing required fields return 400 Bad Request
- Server errors return 500 Internal Server Error

## License

This project is part of a Rockwell assignment.

## Notes

- Numbers must be in the range 1-20
- All words in each mapping are unique
- The application is designed to be extensible for adding new mappings
