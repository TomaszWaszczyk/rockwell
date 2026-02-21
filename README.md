[![Run in Postman](https://voyager.postman.com/logo/postman-logo-icon-orange.svg)](https://app.getpostman.com/run-collection/5091521-f2beda7d-f3d9-5aac-f70f-d694ef32c174) [Run in Postman](https://app.getpostman.com/run-collection/5091521-f2beda7d-f3d9-5aac-f70f-d694ef32c174)

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
5. **Countries** - USA, Canada, Mexico, Brazil, Argentina, etc.

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

- Java 21 (as specified in pom.xml - learned that Railway.com supports the highest version of Java 21)
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

Application is already deployed using Railway, available: [Rockwell Mapping API](https://rockwell-production.up.railway.app)

[Created Postman collection](https://app.getpostman.com/run-collection/5091521-f2beda7d-f3d9-5aac-f70f-d694ef32c174)

## Error Handling

The application includes global exception handling:
- Invalid mapping names return 400 Bad Request
- Invalid number ranges (outside 1-20) return 400 Bad Request
- Missing required fields return 400 Bad Request
- Server errors return 500 Internal Server Error

## Notes

- Additionally, could be a good idea to implement integration tests using `httpClient`
- Numbers must be in the range 1-20
- All words in each mapping are unique
- The application is designed to be extensible for adding new mappings
