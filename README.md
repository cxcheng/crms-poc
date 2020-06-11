# CRMS GraphQL API POC

This is a Spring Boot application that uses the [GraphQL and GraphiQL Spring Framework Boot Starters](https://github.com/graphql-java-kickstart/graphql-spring-boot).

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

3 resolvers have been defined for the application:
* Root query.
* Person.
* Allotment.

## Build

I have specified OpenJDK14 requirement. Maybe unnecessary.

```
# Compile and package into jar with dependencies
mvn clean package spring-boot:repackage

# Optional: Build Docker image
docker build -t crms-poc .


```

## Run

The application config file is `application.yml`. It is embedded in the app. By default it listens at port 8090.

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

## TO DO

* Current Docker image setup is not usable. No way to pass the runtime params into Docker image. Default localhost does not work within image. Need to look into setting this up with Docker Compose or Kubernetes to communicate with external services.
* Schema needs to be updated to include commands.
