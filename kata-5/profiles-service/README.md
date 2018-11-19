# microservice-dojo-katas - Web Service using Mongo DB
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

This is the solution for the katas # 5 [Web Service using Mongo DB](http://accordance.github.io/microservice-dojo/kata5/service_using_mongo_db.html). In this kata, we are going to create a new service (profiles-service) using Mongo DB to store information of the profiles, such as: full-name and photography. 
## Building a Docker image
In order to build the project and the docker image, just run the following command:
* `gradle clean build docker`  
You can double-check if the image was crated by running:
* `docker images`

## Profiles
There are two profiles defined:
1. _**dev** profile:_ it uses H2 database to store data. Additionally, there is an script to add an record to the database using Flyway when the application start up
2. _**prod** profile:_ it uses MySQL database to store data; Flyway does not load data. This kata is configure to run on Docker, so we have to make sure to explicitly configure the network if we run the example with the docker command

###Creating the docker network
If we haven't run kata-4 containers, we need to configure the network where the service are going to connect before running the containers 

It has to be done only once.
 * `docker network create -d bridge katas-network` for more information visit [docker network create](https://docs.docker.com/engine/reference/commandline/network_create/)
 
We can verify if the network was created by the following command:
 * `docker network ls`

##Running the MongoDB Service
To create a docker container running Mongo DB, we just to run the following command:
* `docker run --name mongo -d -p 27017:27017 --network=katas-network mongo`
If we have a mongo container we want to use and it is running, we can add it to the katas-network in order all the katas are able to see it:
* `docker network connect katas-network mongo`  

#Running the Configuration Server
A running Configuration Server is required because we centralized the configuration file in a GitHub repo since kata-4. Run the following command to create and run the configserver:
* `docker run -d -p 8888:8888 --network=katas-network  --name kata-4-configserver com.joseyustiz.msvcdojo/kata-4/config-server`

If it is already creater, we need only start it:
* `docker start kata-4-configserver`
   
## Starting the profiles-service container
  
1. *Running with **dev** profile:* we only need to start the container, map the service port (8500) and give a name to the container (kata-5-profiles-service-dev)
* `docker run -d -p 8500:8300 -e "SPRING_PROFILES_ACTIVE=dev" --name kata-5-profiles-service-dev com.joseyustiz.msvcdojo/kata-5/profiles-service`

2. *Running with **prod** profile:* we need to make sure the the docker network (katas-network) was created, start the container, give a name to the container (kata-5-profiles-service-prod), and map the service port 8600 to 8300 to prevent conflict with kata-5-profiles-service-dev if it is still running.
* `docker run -d -p 8400:8101 -e "SPRING_PROFILES_ACTIVE=prod" --network=katas-network  --name kata-5-profiles-service-prod com.joseyustiz.msvcdojo/kata-5/profiles-service`
 
  ##Testing the account service
  We can test with **curl**. If the **dev** container is running, use the following command:
  * `curl http://localhost:8300`
  * `curl http://localhost:8300/actuator`
  * `curl http://localhost:8300/accounts`
  * `curl http://localhost:8100/accounts/1`
  * `echo '{"username": "jose"}' | curl -H "Content-type:application/json" -d @- http://localhost:8300/accounts`
  * `echo '{"username": "dave"}' | curl -H "Content-type:application/json" -d @- http://localhost:8300/accounts`
  * `curl http://localhost:8300/accounts/search/findByUsername\?username\=jose`
  * `curl http://localhost:8300/accounts/search/findByRole\?role\=user`
 
 If the **prod** is running, change the port to **8400**. 
## Cleaning up 
 In order to prevent to have a conflict the next time we start the container with the same name (kata-4), we can delete the image.
1. List the containers running:
* `docker ps`
2. Stop the containers running:
 First, let's stop the container
 * If kata-4-dev is running `docker stop kata-4-dev`
 * If kata-4-prod is running `docker stop kata-4-prod`
 
 Now, we can delete the containers
 * To delete kata-4-dev run: `docker rm kata-4-dev`
 * To delete kata-4-prod run: `docker rm kata-4-prod`
 
 Another interested docker command is `docker logs <CONTAINER_NAME>`, which allows to see the logs of the containers; e.g.
 * `docker logs kata-4-prod` 