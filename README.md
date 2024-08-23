# Whispers.API

microblogger app using MySQL, Java, Spring Boot, HTML/CSS, JavaScript

## Running with Docker
* Make sure you have docker installed locally https://www.docker.com/get-started/
* Make sure `spring.datasource.url=jdbc:mysql://mysql:3306/spit-db` is added to application.properties
* Remove or comment out `volumes:` for `mysql` container in `docker-compose.yaml` if you do not care to persist data across sessions.
* Run `mvn clean install package` to generate target jar file
* Run `docker-compose build` to build docker image
* Run `docker-compose up` to run application with docker

# UML

![whispersUML](https://github.com/user-attachments/assets/83f981f2-e7e5-47ab-966a-1ba426c5f240)

# Front End:

![whispers](https://github.com/user-attachments/assets/6f067508-797f-4561-96c1-5891bdac0c24)

