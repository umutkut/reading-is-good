##TECH STACK & APPROACH
- Java 11
- SpringBoot
- PostgreSQL
- Docker
- Domain Driven Design
- Postman
- Lombok
- Mockito
- Guava
- Jackson

##OPEN TO IMPROVEMENT
- Test extensions to improve covarage and one fix needed.
- Spring security or another authentication process needed.
- Paging might be implemented on possible large responses.
- Endpoint documentation.

##RUNNING THE APPLICATION
- Clone the project or download `docker-compose.yml` to local directory.
- Open a terminal in the directory and run bellow:
  - `docker-compose --profile prod up -d`

###OR RUN IN DEVELOPMENT
- Clone the project and open with prefered IDE.
- Run bellow:
  - `docker-compose --profile dev up -d`
- Run the maven application.

##ENDPOINTS
- Postman requests available in the path:
  - `postman-requests/ReadingIsGood.postman_collection.json`