# microservice-dojo-katas - Microservice with personal DB and database migrations
[![Build Status](https://travis-ci.org/joseyustiz/microservice-dojo-katas.svg?branch=master)](https://travis-ci.org/joseyustiz/microservice-dojo-katas)
![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=com.joseyustiz.msvcdojo%3Amysvc&metric=alert_status)
![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=com.joseyustiz.msvcdojo%3Amysvc&metric=bugs)
![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=com.joseyustiz.msvcdojo%3Amysvc&metric=code_smells)
![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=com.joseyustiz.msvcdojo%3Amysvc&metric=coverage)
![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=com.joseyustiz.msvcdojo%3Amysvc&metric=duplicated_lines_density)
![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=com.joseyustiz.msvcdojo%3Amysvc&metric=ncloc)
![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=com.joseyustiz.msvcdojo%3Amysvc&metric=sqale_rating)
![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=com.joseyustiz.msvcdojo%3Amysvc&metric=reliability_rating)
![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=com.joseyustiz.msvcdojo%3Amysvc&metric=security_rating)
![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=com.joseyustiz.msvcdojo%3Amysvc&metric=sqale_index)
![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=com.joseyustiz.msvcdojo%3Amysvc&metric=vulnerabilities)

This is the solution for the katas # 3 [Microservice with personal DB and database migrations](http://accordance.github.io/microservice-dojo/kata3/service_using_mysql_db.html) 
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
Before running the containers we have to create the docker network to make sure the MySQL and the kata-3 (account-service) containers are able to communicate 

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

## Starting the kata-3 container
  
1. *Running with **dev** profile:* we only need to start the container, map the service port (8300) and give a name to the container (kata-3-dev)
* `docker run -d -p 83400:8300 -e "SPRING_PROFILES_ACTIVE=dev" --name kata-3-dev com.joseyustiz.msvcdojo/kata-3`

2. *Running with **prod** profile:* we need to make sure the the docker network (katas-network) was created, start the container, give a name to the container (kata-3-prod), and map the service port 8300 to 8400 to prevent conflict with kata-3-dev if it is still running.
* `docker run -d -p 8400:8300 -e "SPRING_PROFILES_ACTIVE=prod" --network=katas-network  --name kata-3-prod com.joseyustiz.msvcdojo/kata-3`
 
  ##Testing the account service
  We can test with **curl** if the service is running by the following command
  * `curl http://localhost:8300`
  * `curl http://localhost:8300/actuator`
  * `curl http://localhost:8300/accounts`
  * `curl http://localhost:8100/accounts/1`
  * `echo '{"username": "jose"}' | curl -H "Content-type:application/json" -d @- http://localhost:8300/accounts`
  * `echo '{"username": "dave"}' | curl -H "Content-type:application/json" -d @- http://localhost:8300/accounts`
  * `curl http://localhost:8300/accounts/search/findByUsername\?username\=jose`

## Cleaning up 
 In order to prevent to have a conflict the next time we start the container with the same name (kata-3), we can delete the image.
1. List the containers running:
* `docker ps`
2. Stop the containers running:
 First, let's stop the container
 * If kata-3-dev is running `docker stop kata-3-dev`
 * If kata-3-prod is running `docker stop kata-3-prod`
 
 Now, we can delete the containers
 * To delete kata-3-dev run: `docker rm kata-3-dev`
 * To delete kata-3-prod run: `docker rm kata-3-prod`