# microservice-dojo-katas - Externalizing configuration
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

This is the solution for the katas # 4 [Externalizing configuration](http://accordance.github.io/microservice-dojo/kata4/externalizing_configuration.html). In this kata we are going to use Spring Configuration to externalize the configuration of the services. 
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
Before running the containers we have to create the docker network to make sure the MySQL and the kata-4 (accounts-service) containers are able to communicate 

It has to be done only once.
 * `docker network create -d bridge katas-network` for more information visit [docker network create](https://docs.docker.com/engine/reference/commandline/network_create/)
 
We can verify if the network was created by the following command:
 * `docker network ls`
 If the MySQL is already running, we can add it to the network by the following command: 
 * `docker network connect katas-network demo-mysql`
 
 ##Running the MySQL Service
 If the MySQL is not runnig by the following command we can start it and add to the `katas-network`: 
 * `docker run --name demo-mysql -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=demo -e MYSQL_USER=demo_user -e MYSQL_PASSWORD=demo_pass -p 3306:3306 --network=katas-network -d mysql:latest`
 
 We can verify whether the demo-mysql container is up and running by the following command:
 * `docker logs demo-mysql`

## Starting the kata-4 container
  
1. *Running with **dev** profile:* we only need to start the container, map the service port (8300) and give a name to the container (kata-4-dev)
* `docker run -d -p 83400:8300 -e "SPRING_PROFILES_ACTIVE=dev" --name kata-4-dev com.joseyustiz.msvcdojo/kata-4/account-service`

2. *Running with **prod** profile:* we need to make sure the the docker network (katas-network) was created, start the container, give a name to the container (kata-4-prod), and map the service port 8300 to 8400 to prevent conflict with kata-4-dev if it is still running.
* `docker run -d -p 8400:8300 -e "SPRING_PROFILES_ACTIVE=prod" --network=katas-network  --name kata-4-prod com.joseyustiz.msvcdojo/kata-4/account-service`
 
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
 
 * `docker run -d -p 8888:8888 --network=katas-network  --name kata-4-configserver com.joseyustiz.msvcdojo/kata-4/config-server` 
 * `docker run -d -p 8400:8300 -e "SPRING_PROFILES_ACTIVE=prod" --network=katas-network  --name kata-4-prod com.joseyustiz.msvcdojo/kata-4/account-service`