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
- 

W2D1: We started the week by covering messaging systems, starting off with traditional messaging system then moving to Kafka. We discussed the architecture of Kafka, including topics, partitions, brokers, producers, and consumers. And how Kafka is differnt from traditional messaging systems like JMS, ActiveMQ, RabbitMQ.

W2D2: We covered scheduling in Spring Boot, including how to use cron expressions and the @Scheduled annotation. We learned how to produce and consume application events using ApplicationEventPublisher and @EventListener. We also discussed logging best practices, including the use of different log levels.

W2D3: We explored testing strategies in Spring Boot, focusing on unit testing with JUnit and Mockito and Hamcrest for assertions. We also discussed repository tests, integration testing using RestAssured for testing RESTful APIs. Also we learned the best practices for writing test called FIRST

W2D4: We started by discussing the importance of monitoring in enterprise applications. We explored the ELK stack (Elasticsearch, Logstash, Kibana) as a powerful solution for log management and analysis. We also covered Prometheus and Grafana for metrics monitoring and visualization. We learned how to integrate these tools into a Spring Boot application to gain insights into application performance and health.

SCI: As we learned how to developed layered application using Spring Boot in previous courses, we learned how to write for each of those layers, from unit test, repository test, integration test. This is relevant to principle 2: Order is present in everywhere. Also when broker successfully sent the message to consumer, consumer will send an ack back to broker and manage their own offset in the topic, this is relevant to principle 8: Every action has a reaction.