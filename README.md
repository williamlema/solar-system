# solar-system

## Requirements
For building and running the application you need:

* [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Maven](https://maven.apache.org/download.cgi)

## Configuration

This application is running using H2 in memory database, all properties required are already set in properties file

### Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the following command in root directory.

```
$ mvn clean package 
$ java -jar target/solar-system-0.0.1-SNAPSHOT.jar

```

### Running the application locally with Docker

#### Prerequisites

#### Docker

Docker and Docker Compose are used to build and deploy this application,.

Installation instructions for Docker can be found on its [Get Started](https://docs.docker.com/get-started/) page.

If your installation of Docker did not come with Docker Compose, you can follow its
[install instructions](https://docs.docker.com/compose/install/).

#### Running

To start the application with docker run the following command over root folder:

```
$ docker-compose up
```

The previous command will download the source project from [Solar System repository](https://github.com/williamlema/solar-system) and compile the code in order to deploy the application in a container


The Api will run over http://localhost:9081/, to test the api you can use the request in `Local` folder in the postman file [Solar System.postman_collection.json](https://github.com/williamlema/solar-system/blob/master/src/main/resources/Solar%20System.postman_collection.json) located at `resources` folder

## Cloud hosting

This application is running in AWS Elastic Beanstalk over the following DNS [AWS Solar System](http://solarsystem-env.eba-xzagkmjh.us-west-2.elasticbeanstalk.com/), to test the application in aws you can the request in `Cloud` folder in the postman file [Solar System.postman_collection.json](https://github.com/williamlema/solar-system/blob/master/src/main/resources/Solar%20System.postman_collection.json) located at `resources` folder