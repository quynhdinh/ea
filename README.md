# Enterprise Application with Java and Spring Boot
Enterprise Architecture
- [Enterprise Application with Java and Spring Boot](#enterprise-application-with-java-and-spring-boot)
  - [Spring AOP](#spring-aop)
  - [Messaging](#messaging)
  - [Scheduling, Events, Logging](#scheduling-events-logging)
  - [Monitoring](#monitoring)
    - [Actuator](#actuator)

## Spring AOP
Joinpoint: a point in the execution of a program, such as the execution of a method or the handling of an exception.

Pointcut: a predicate that matches join points. It is used to specify where advice should be applied.

Aspect is the combination of advice and pointcut: what advice to be exec on what pointcut 
Weaving is seen at execution time
Types of advice: 

`@Before`

`@After`

`@AfterReturning`: only execs if returns properly

`@AfterThrowing`: only exec if an exception thrown

`@Around`: single advice method

Rod johnson book

`Surrogate key`: key should have no ideas

`@Version` column: optimistic locking

Repository, service, configuration inherits from @component

Service sent events: lightway alternative to ws, 1 way, server-> client streaming

People criticize java for its anachronistic syntax but Java 21
- sealed types, pattern matching, records, smart switch, data oriented programming, virtual thread
Java development best practices
- RAII: C/C++ initialize a resource and release it when done
## Messaging
`Offset`: Every consumer manages their own offset in the topic
Brokers manage their own message consumed ?

How do consumers keep track of messages ?
When a consumer consumes a message, will there be a commit from the consumer back to the broker ? when it dies, it can asked the last commit, you can enable auto commiting

Problems with jms
- It allows broker upgrades that can break wire protocol, forcing both producer and consumer to upgrade simultaneously,
- Producer and consumer share the same address, making routing between them difficult
- JMS require server client have to together upgrade

Four main integration styles discussed in enterprise integration?

What are the advantages of messaging architecture ?
- Async by default
- Provide decoupling
- Allows system to operate independently
- Enable fire and forget communication
- Does not require system to be at the same place at the same time
- Pipes and filters architecture

Why is RPC considered a problematic integration style?
- It makes synchronous method invocations that fail if the remote service disappears
A traditional messaging system provides guaranteed delivery by storing messages on disk until they are successfully processed by the consumer(ActiveMQ, RabbitMQ)
Staged event-driven architecture
IMAP protocol

If the consumer is slower than the producer, the messages will pile up in the queue -> not fault tolerant

Kafka. If the topic is full, scale out partitions across multiple brokers, also Kafka supports replication that means each partition can be replicated across multiple brokers. Leaders replicate messages to followers,...

## Scheduling, Events, Logging
- Cron expression 6 or 7 fields second, minute, hour, day of month, month, day of week, year (optional)
- @ConfigurationProperties(prefix="app.scheduling"). You can then @Autowired AppProperties and access the properties
## Monitoring
### Actuator
 