#!/usr/bin/env bash

curl -v http://localhost:8080/reservations \
  -H "Content-Type: application/json" \
  -d "{\"at\": \"2022-10-21T19:00\", \"email\": \"caravan@example.com\", \"name\": \"Cara van Palace\", \"quantity\": 3}"
