# Enterprise Application with Java and Spring Boot
Enterprise Architecture
- [Enterprise Application with Java and Spring Boot](#enterprise-application-with-java-and-spring-boot)
  - [Spring AOP](#spring-aop)
  - [Messaging](#messaging)
  - [Scheduling, Events, Logging](#scheduling-events-logging)
  - [Monitoring](#monitoring)
    - [Actuator](#actuator)
  - [Architecting for modularity](#architecting-for-modularity)
    - [Event based integrations](#event-based-integrations)
    - [@Async](#async)
    - [Outbox pattern](#outbox-pattern)
  - [Microservices with Spring Cloud](#microservices-with-spring-cloud)
  - [Security](#security)

## Spring AOP
`Joinpoint`: a point in the execution of a program, such as the execution of a method or the handling of an exception.

`Pointcut`: a predicate that matches join points. It is used to specify where advice should be applied.

`Aspect` is the combination of advice and pointcut: what advice to be exec on what pointcut
`Weaving` is seen at execution time
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

People criticize java for its anachronistic syntax but Java 21 comes with many modern features
- `sealed types`, `pattern matching`, `records`, `smart switch`, `data oriented programming`, `virtual thread`

Java development best practices
- `RAII`: C/C++ initialize a resource and release it when done
- Spring boot features

  When Spring boots starts up, it looks for `spring.factories` and `AutoConfiguration.imports` having all the auto configuration classes, and then it loads them in order. Each auto configuration class has a conditional annotation(`@ConditionalOnMissingBean`, `@ConditionalOnClass`, `@ConditionalOnProperty` which allows dynamic configuration based on the presence of certain classes, beans or specific environment setup at RUNTIME)

  For example: `spring-boot-starter` is pre-packaged dependencies that automatically configure components when added to the classpath, such as setting up a web server, installing Spring MVC, and configuring JSON marshalling without additional code
## Messaging
`Offset`: Every consumer manages their own offset in the topic
Brokers manage their own message consumed ?

How do consumers keep track of messages ?
- Each consumer maintains an offset, which is a pointer to the last message it has processed in a partition. This offset is stored in a special topic called `__consumer_offsets`, allowing consumers to resume processing from where they left off in case of failures or restarts. `auto commit` means the consumer will commit the offset automatically at specified intervals, while manual commit gives the application control over when offsets are committed, allowing for more precise handling of message processing and retries.

Problems with JMS ?
- It allows broker upgrades that can break wire protocol, forcing both producer and consumer to upgrade simultaneously,
- Producer and consumer share the same address, making routing between them difficult
- JMS require server client have to together upgrade

Four main integration styles discussed in enterprise integration ?
- File transfer
- Shared database
- Remote procedure invocation
- Messaging
  
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
- `@ConfigurationProperties(prefix="app.scheduling")`. You can then @Autowired AppProperties and access the properties
## Monitoring
### Actuator
## Architecting for modularity
### Event based integrations
- `Event Notification`: A messaging approach where the event message simply notifies the consumer that something has happened, and the consumer must then make a separate call to the producer to get the details of what happened, leading to tight coupling between producer and consumer
- `Event carried state transfer`: A messaging approach where each event message contains actionable details that the consumer can use, avoiding coupling between the producer and consumer by including all necessary information within the message itself
- `Event sourcing`: A pattern where every change to the system state is captured as a sequence of events, allowing for system reconstruction, historical replay, and the ability to apply new algorithms retroactively to past events
- `CQRS`: Command Query Responsibility Segregation (CQRS) represents the concept of using different data structures optimized for different read and write modalities, such as using Postgres for writes, Redis for fast reads, and Elasticsearch for full-text search
### @Async
The `@Async` annotation wraps the method invocation in a separate thread, allowing the original method to return immediately while the event is processed in the background, providing immediate responsiveness but potentially risking state loss.
### Outbox pattern
`@ApplicationModuleListener`: come from Spring Modulith, it created a table called event_publisher, having columns like(event_id, listener_id, event_type, serialized event presentation, publication_date and completion_date). Once the event is published, it is stored in the table with publication_date, and a separate process reads from this table and publishes the event to the message broker, updating the completion_date once done. This ensures that events are reliably published even if the main transaction fails.

Outbox pattern helps reconciling non-transactional resources against transactional ledger. Ensuring event delivery and consistency by storing events in a database table and guaranteeing their eventual processing, even if initial delivery fails.
And then when multiple service instances restart and attempt to resubmit the same event, messages may be published multiple times. => use a lock registry

Spring Modulith.
- Messaging module that supports externalization, which can publish messages to different brokers like Kafka, RabbitMQ, JMS, SQS, Pulsar and other using Spring Integration.
- Uses ArchUnit which allows testing and enforcing architectural rules and constraints in the system.
- Organize code around features rather than technical roles, keeping related concerns together, and being deliberate about what is made public(No different controllers, repositories, services, entities packages(then you have to make them public)).
- If a nested package type is injected into another module, the test will fail, indicating that an implementation detail of one module has been inadvertently leaked into another module.
- C4 component diagram modeling system boundaries and components generating using PlantUML.

## Microservices with Spring Cloud
## Security
`OncePerRequestFilter`: A specialized filter that ensures it is executed only once per request, even if the request passes through multiple filters in the filter chain. This is useful for tasks that should only be performed once, such as setting up security context or logging.

`SecurityFilterChain`: A configuration that defines the order and behavior of security filters in a web application. It allows you to customize how requests are processed and secured by specifying which filters to apply and in what order.

`UserDetailsService`: is a interface has a single method, loadUserByUsername(String username), which is used to retrieve user details from a data source, such as a database. This method returns a UserDetails object that contains information about the user, including their username, password, and authorities (roles/permissions).
