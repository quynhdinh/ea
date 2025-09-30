docker run --name spring-boot-postgres-db \
-e POSTGRES_DB=homework_db \
-e POSTGRES_USER=postgres \
-e POSTGRES_PASSWORD=postgres \
-p 5433:5432 \
-v pgdata:/var/lib/postgresql/data \
--restart always \
-d pgvector/pgvector:0.8.1-pg17-trixie

# script to execute psql command inside the container
docker exec -it spring-boot-postgres-db psql -U postgres -d homework_db