#!/bin/bash

./create-db.sh
cd ..
mvn liquibase:update
