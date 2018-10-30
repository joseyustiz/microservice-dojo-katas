# microservice-dojo-katas 
[![Build Status](https://travis-ci.org/joseyustiz/microservice-dojo-katas.svg?branch=master)](https://travis-ci.org/joseyustiz/microservice-dojo-katas) 
![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=joseyustiz_microservice-dojo-kata1&metric=alert_status)
![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=joseyustiz_microservice-dojo-kata1&metric=bugs)
![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=joseyustiz_microservice-dojo-kata1&metric=code_smells)
![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=joseyustiz_microservice-dojo-kata1&metric=coverage)
![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=joseyustiz_microservice-dojo-kata1&metric=duplicated_lines_density)
![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=joseyustiz_microservice-dojo-kata1&metric=ncloc)
![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=joseyustiz_microservice-dojo-kata1&metric=sqale_rating)
![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=joseyustiz_microservice-dojo-kata1&metric=reliability_rating)
![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=joseyustiz_microservice-dojo-kata1&metric=security_rating)
![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=joseyustiz_microservice-dojo-kata1&metric=sqale_index)
![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=joseyustiz_microservice-dojo-kata1&metric=vulnerabilities)

This repository contains solutions for the katas defined at [Microservices-Dojo](http://accordance.github.io/microservice-dojo/). This project contains multiples subproject, one for each kata; The katas are the following: 

| Kata # | Description and Solution Proposed by the Author | My Solution | Tech Stack |
| :----: | ----------- |------------| ---------- |
|  1     | [Building Simple Web Service with Spring Boot](http://accordance.github.io/microservice-dojo/katas/creating_basic_web_service.html) | [Kata-1](https://github.com/joseyustiz/microservice-dojo-katas/tree/master/kata1)| Spring Boot |
|  2     | [Dockerizing Application](http://accordance.github.io/microservice-dojo/kata2/dockerizing_application.html) | [Kata-2](https://github.com/joseyustiz/microservice-dojo-katas/tree/master/kata-2) | Docker |
|  3     | [Microservice with personal DB and database migrations](http://accordance.github.io/microservice-dojo/kata3/service_using_mysql_db.html) |  | Flyway |
|  4     | [Externalizing configuration](http://accordance.github.io/microservice-dojo/kata4/externalizing_configuration.html) |  |  |
|  5     | [Web Service using Mongo DB](http://accordance.github.io/microservice-dojo/kata5/service_using_mongo_db.html) |  |   |
|  6     | [Service talking to another Service](http://accordance.github.io/microservice-dojo/kata6/service_talking_to_service.html) |  | Eureka, Ribbon and Feign |
|  7     | [Circuit Breaker pattern](http://accordance.github.io/microservice-dojo/kata7/circuit_breakers.html) |  | Hystrix |
|  8     | [Edge Service](http://accordance.github.io/microservice-dojo/kata8/edge_service.html) |  | Zuul |

Complimentary Katas:

| Kata # | Description and Solution Proposed by the Author | Tech Stack |
| :----: | ----------- | ---------- |
|  a     | [Getting Started with Web client](http://accordance.github.io/microservice-dojo/kata-web-client/web-client-basics.html) | Angular |
|  b     | [Simple Orchestration](http://accordance.github.io/microservice-dojo/kata-dev-environment/simple-orchestration.html) |   |
|  c     | Distributed Logging | ELK Stack |
|  d     | Distributed Tracing | Zipkin |
|  e     | [Mesos and Marathon framework](http://accordance.github.io/microservice-dojo/kata-mesos/scheduling_with_mesos.html) | Mesos, Marathon  |

Contributed Katas:

| Kata # | Description and Solution Proposed by the Author | Tech Stack |
| :----: | ----------- | ---------- |
|  a     | [Documenting RESTful APIs with Spring REST Docs](http://accordance.github.io/microservice-dojo/kata-spring-restdocs/spring-restdocs-intro.html) | Spring REST Docs |
|  b     | [Embedding live service contracts documentation with Swagger](http://accordance.github.io/microservice-dojo/kata-swagger/swagger_api_doc.html) |  Swagger |
|  c     | [Aggregating Container Logs using Elasticsearch, Logbeat and Kibana](http://accordance.github.io/microservice-dojo/kata-elk/elk.html) |  ELK Stack |
  
## Generating Test Report
Because the test scripts are in each submodule the report are in the `build/reports/tests/test`. In order to generate and open the test report of the application you execute the following commands: 
* `gradle test`, it can be run in the project or submodule level
* `open kata#/build/reports/tests/test/index.html`, where # is the number of the kata
## Usage
In order to run the application you execute the following commands: 
* `gradle build`, it can be run in the project or submodule level
* `java -jar kata#/build/libs/mysvc-0.0.1-SNAPSHOT.jar`.
## Healthcheck
Let’s health-check our brand new service:
* `curl http://localhost:8100/actuator/health`