#!/bin/bash
# Example POST request to create a new product
curl -X POST -H "Content-Type: application/json" -d '{"name":"Laptop","description":"High-performance laptop","price":1200.00}' http://localhost:8080/api/products