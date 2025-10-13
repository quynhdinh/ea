To prepare for the database run the following command:
```bash
#!/bin/bash
docker run --name spring-boot-postgres-db \
-e POSTGRES_DB=homework_db \
-e POSTGRES_USER=postgres \
-e POSTGRES_PASSWORD=postgres \
-p 5433:5432 \
-v pgdata:/var/lib/postgresql/data \
--restart always \
-d pgvector/pgvector:0.8.1-pg17-trixie
```
The run the following command to start the application provided that you have Maven installed:
```bash
mvn spring-boot:run
```

Run the following command to get the token
```bash
curl -i -X POST -H "Content-Type: application/json" -d '{"username":"admin_db","password":"admin_db"}' http://localhost:8080/authenticate
```
Then enter the token in the following command to get the books
```bash
curl -i -X GET -H "Authorization: Bearer XYZ" http://localhost:8080/api/books
```