# microservice-dojo-katas - Dockerizing Application
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

This is the solution for the katas # 3 [Microservice with personal DB and database migrations](http://accordance.github.io/microservice-dojo/kata3/service_using_mysql_db.html) 
## Building a Docker image
In order to build the project and the docker image, just run the following command:
* `gradle build docker`  
You can double-check if the image was crated by running:
* `docker images`

## Starting the container
 We need to star the container, map the service port (8100) and give a name to the container (kata-3)
 * `docker run -d -p 8100:8100 --name kata-3 com.joseyustiz.msvcdojo/kata-3`
 You can test with `curl` if the service is running by the following command
 * `curl http://localhost:8100`
 
 ## Cleaning up 
 In order to prevent to have a conflict the next time you start the container with the same name (kata-3), you can delete the image.
 
 First, let's stop the container
 * `docker stop kata-3`
 
 Now, you can delet the container
 * `docker rm kata-3`