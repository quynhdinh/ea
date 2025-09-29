#!/bin/bash
# run with no banner and logs
mvn spring-boot:run -Dspring-boot.run.arguments=--spring.main.banner-mode=off,--logging.level.root=ERROR
