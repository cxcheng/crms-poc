# CRMS GraphQL API POC

This is a Spring Boot application that uses the [GraphQL and GraphiQL Spring Framework Boot Starters](https://github.com/graphql-java-kickstart/graphql-spring-boot). The Spring Boot application internally uses [Spring Boot Webflux](https://spring.io/guides/gs/reactive-rest-service) to make REST calls to external microservices to supply the data.

The GraphQL schema is as follows:
```
type Query {
    info(uens: [UEN]): [Person]
}

input UEN {
    nric: String
    name: String
    mobile: String
}

enum MhaAddressType {
    APARTMENT_BLK
    WITHOUT_APARTMENT_BLK
    REVERSE_OF_APARTMENT_BLK_STREET_NAME
    OVERSEAS_ADDRESS
    PRIVATE_FLATS_WITH_APARTMENT_BLK
    CO_APARTMENT_BLK
    CO_WITHOUT_APARTMENT_BLK
    QUARTER_ADDRESS
    ISLAND_ADDRESS
    SPACE
}

type MhaAddress {
    type: MhaAddressType
    blockNumber: String
    streetCode: String
    streetName: String
    floor: String
    unit: String
    buildingName: String
    postalCode: String
    newPostalCode: String
}

type Person {
    id: ID!
    nric: String!
    name: String!
    nationalityType: String!
    address: MhaAddress
    allotments: [Allotment]
}

type Allotment {
    id: ID!
    scheme: String!
    subScheme: String!
    year: Int!
    cycle: String!
    amount: Float!
    address: MhaAddress
}
```

3 resolvers have been defined for the application.
* Root query.
* Person.
* Allotment.

The Person and Allotment resolvers each maps to one REST microservice. If there are additional microservices, we can add more resolvers.

## Build

I have specified OpenJDK14 requirement. Maybe unnecessary.

```
# Compile and package into jar with dependencies
mvn clean package spring-boot:repackage

# Optional: Build Docker image
docker build -t crms-poc .
```

## Run

The application config file is `application.yml`. It is embedded in the app. By default it listens at port 8090 and contacts the Person and Allotment microservices at http://localhost:8080.

```
# Run without building jar
mvn spring-boot:run

# Run java jar directly
java -jar target/crms-poc-0.0.1-SNAPSHOT.jar

# Run java jar directly specifying URLs for services
java -jar target/crms-poc-0.0.1-SNAPSHOT.jar --server.port=8081 --services.person.url=http://localhost:8080 --services.allotment.url=http://localhost:8080

# Run Docker image if built
docker run -it --rm -p 8090:8090 crms-poc
``` 

## Sample Test Run

1. Start servers.
```
# Run Wei Kang's WireMock Docker image locally in the background
docker run -it --rm -p 8080:8080 cds-mock

# Start app
mvn spring-boot:run
```

2. Open browser and type `http://localhost:8090/graphiql`

3. Type in following query.
```
{
  info(uens: [{nric: "S1111111A"}]) {
    nric
    name
    address {
      blockNumber
      streetCode
      streetName
      floor
      unit
      buildingName
      postalCode
      newPostalCode
    }
    nationalityType
    allotments {
      scheme
      subScheme
      year
      address {
	blockNumber
        streetCode
        streetName
    	floor
      	unit
      	buildingName
      	postalCode
      	newPostalCode
      }
    }
  }
}
```


## To Do

* Current Docker image setup is not usable: the application is not able to communicate to external services easily. The ideal way to do so would be using Docker Compose or Kubernetes.
* Implementation is basic. Lacking more complete and defensive programming practices.
   * No timeout or other more robust approaches to handle problems with REST services.
   * No checking for bad inputs such as invalid NRIC.
   * Error handling is very simple and root errors are not propagated to the caller.
* No unit tests.
