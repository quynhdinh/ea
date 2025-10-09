#!/bin/bash
docker run -d --name my-mongo-db -p 27017:27017 -e MONGO_INITDB_DATABASE=productdb mongo:latest