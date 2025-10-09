# Monitoring Application with ELK Stack
Moving to the source code
```bash
cd monitoring-app
```
Use docker compose to start the ELK stack:
```bash
docker-compose -f src/main/resources/docker-compose.yml up
```
Run the Spring Boot application from your IDE or using:
```bash
mvn spring-boot:run
```
Run these endpoints several times to generate logs:
- [http://localhost:8080/info-log](http://localhost:8080/info-log) - Generates an INFO log.
- [http://localhost:8080/error-log](http://localhost:8080/error-log) - Generates an ERROR log.