# Student's queue

### Postman commands for docker

###### Inject Data:
- Post: localhost:8081/injector

###### Student:
- Post: localhost:8081/students/?name={name}
- Get: localhost:8081/students/{id}
- Get: localhost:8081/students/getAll
- Get: localhost:8081/students/getQueue
- Put: localhost:8081/students/{id}   
  body {"name":"Name","numberInQueue":"null"}
- Put: localhost:8081/students/leaveFromQueue/{id}
- Put: localhost:8081/students/addToQueue/{id}
- Delete: localhost:8081/students/{id}

###### Authentication:
- Get: localhost:8081/login?name={name}

## Implementation details and technologies

### Project based on 3-layer architecture:
>- Presentation layer (controllers)
>- Application layer (services)
>- Data access layer (DAO)

### Technologies
- Java 11
- Spring Boot
- Spring Boot WEB
- Spring Boot DATA
- Hibernate
- PostgreSQL
- JSON
- Lombok
- Maven
- Docker

### Diagram DB
![drawing](http://dl4.joxi.net/drive/2022/04/26/0052/3292/3415260/60/2bf403898b.jpg)

## Setup
>- 1Install PostgreSQL(V - 14.0)
>- 2In the src/main/resources/application.properties file change properties to the ones you specified when installing PostgreSQL
>- 3. add TomCat configuration to run project and start

## Setup by docker
Execute commands in the terminal :
- mvn clean package
- docker-compose up
