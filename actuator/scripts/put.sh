#!/bin/bash
curl -X PUT -H "Content-Type: application/json" -d '{"name":"Laptop Pro","description":"Updated high-performance laptop","price":1350.00}' http://localhost:8080/api/products/{id}