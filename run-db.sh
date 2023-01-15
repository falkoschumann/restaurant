#!/usr/bin/env bash

docker run --name restaurant_db --publish 5432:5432 -e POSTGRES_DB=restaurant -e POSTGRES_USER=restaurant -e POSTGRES_PASSWORD=restaurant -d postgres
