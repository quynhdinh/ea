MSG-Sender application is a Spring Boot application that sends messages to a Kafka topic. The following demonstrate how to test parts A, B, C of the lab using the modified W2D1-MSG-Sender application.
Run the following command to start Kafka using docker
```bash
docker run -d -p 9092:9092 --name kafka-broker apache/kafka:latest
```
Run the following commands to see the messages being sent to Kafka topic "book":
```bash
docker exec --workdir /opt/kafka/bin/ -it kafka-broker sh
```
Once you inside the container, run the following command to see the messages being sent to Kafka topic "book":
```bash
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic book --from-beginning
```
- A: leave them as is, it will pick a random book in the books variable and send to kafka topic "book"
- B: uncomment the line updateBook(book) in the run method of the Sender class, it will update the book title to "Updated Title" before sending to kafka topic "book"
- C: uncomment the line sendDelete(1) in the run method of the Sender class, it will send a delete message to kafka topic "book" with book id 1