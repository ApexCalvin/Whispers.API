# Whispers.API - microblogger app

Utilized Java, Spring Boot, and MySQL to develop an online forum that allows users to post and filter messages
by hashtags and users. Implemented CRUD operations and demonstrated entity relationships, such as @ManyToMany
and @OneToMany in Spring Boot. Integrated with an external SQL database, created REST APIs, and ensured 100% test
coverage on methods using JUnit and Mockito. Built a simple UI with JavaScript, HTML, and CSS

# Front End:

![whispers ui](https://github.com/user-attachments/assets/00b0a7d1-c7e3-4f88-b75c-8d14a50acce5)

# UML

![whispersUML](https://github.com/user-attachments/assets/83f981f2-e7e5-47ab-966a-1ba426c5f240)

## Running with Docker
* Make sure you have docker installed locally https://www.docker.com/get-started/
* Make sure `spring.datasource.url=jdbc:mysql://mysql:3306/spit-db` is added to application.properties
* Remove or comment out `volumes:` for `mysql` container in `docker-compose.yaml` if you do not care to persist data across sessions.
* Run `mvn clean install package` to generate target jar file
* Run `docker-compose build` to build docker image
* Run `docker-compose up` to run application with docker
