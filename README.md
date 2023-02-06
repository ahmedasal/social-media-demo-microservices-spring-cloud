# Social Media Demo


## Goal
Learning microservices using spring cloud, database design and queries practice. 

## Description
Implementing social media app using old architecture using SQL database  and queries to load user feed.
This architecture has already reported problems and replaced with a new architecture [this project by mostafacs](https://github.com/mostafacs/social-media-quarkus-microservices-kubernetes).

## Project Dependency
- MySql Database. 
- java version 17.

## Security
Using keycloak v20.0.3 as authorization server.

## How to Run
```shell
    mvn clean install
    # run configuration server
    java -jar config-server-0.0.1-SNAPSHOT.jar
    # run discovery service 
    java -jar discovery-service-0.0.1-SNAPSHOT.jar
    # run gateway 
    java -jar gateway-0.0.1-SNAPSHOT.jar
    # run monitoring
    java -jar monitoring-0.0.1-SNAPSHOT.jar
    # run users service
    java -jar users-0.0.1-SNAPSHOT.jar
    # run feed service
    java -jar feed-0.0.1-SNAPSHOT.jar
```

## Endpoints

Services | Endpoint | Description
:-- | :-- | :--
Register user | http://localhost:9090/users/api/user | add user to keycloak
Login user | http://localhost:9090/users/api/user/login | login user
get user | http://localhost:9090/users/api/user/{userName} | get user from keycloak
feed | http://localhost:9090/feed/api/feed | show feed of user






