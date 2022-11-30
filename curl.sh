#!/bin/bash


curl -X POST -d '{"reference": "REFERENCE1", "account_iban": "IBAN_1111", "date": "2019-07-16T16:55:42.000Z", "amount": 100.2, "fee": 2.5, "description": "Some description"}' -H "Content-Type: application/json" http://localhost:8080/v1/create

echo ""

curl -X POST -d '{"reference": "REFERENCE4", "account_iban": "IBAN_1111", "date": "2022-11-30T16:55:42.000Z", "amount": 100.2, "fee": 2.5, "description": "Some description"}' -H "Content-Type: application/json" http://localhost:8080/v1/create

echo ""

curl -X POST -d '{"reference": "REFERENCE5", "account_iban": "IBAN_1111", "date": "2022-11-29T16:55:42.000Z", "amount": 100.2, "fee": 2.5, "description": "Some description"}' -H "Content-Type: application/json" http://localhost:8080/v1/create

echo ""

curl -X POST -d '{"reference": "REFERENCE2", "account_iban": "IBAN_1111", "date": "2019-07-16T16:55:42.000Z", "amount": 104.2, "fee": 2.5, "description": "Some description"}' -H "Content-Type: application/json" http://localhost:8080/v1/create

echo ""

curl -X POST -d '{"reference": "REFERENCE3", "account_iban": "IBAN_1112", "date": "2019-07-16T16:55:42.000Z", "amount": 101.2, "fee": 2.5, "description": "Some description"}' -H "Content-Type: application/json" http://localhost:8080/v1/create

echo ""

curl -X POST -d '{"account_iban": "IBAN_1111", "date": "2019-07-16T16:55:42.000Z", "amount": 103.2, "fee": 2.5, "description": "Some description"}' -H "Content-Type: application/json" http://localhost:8080/v1/create

echo ""

curl -X GET "http://localhost:8080/v1/search?accountIban=IBAN_1111&sortMode=asc"

echo ""

curl -X GET "http://localhost:8080/v1/search?accountIban=IBAN_1112&sortMode=asc"

echo ""

curl -X GET "http://localhost:8080/v1/search?accountIban=IBAN_1111&sortMode=desc"

echo ""

curl -X POST -d '{"reference": "NOTEXISTINGREFERENCE", "channel": "atm"}' -H "Content-Type: application/json" "http://localhost:8080/v1/status"

echo ""

curl -X POST -d '{"reference": "REFERENCE1", "channel": "atm"}' -H "Content-Type: application/json" "http://localhost:8080/v1/status"

echo ""

curl -X POST -d '{"reference": "REFERENCE4", "channel": "atm"}' -H "Content-Type: application/json" "http://localhost:8080/v1/status"

echo ""

curl -X POST -d '{"reference": "REFERENCE5", "channel": "atm"}' -H "Content-Type: application/json" "http://localhost:8080/v1/status"

echo ""

curl -X POST -d '{"reference": "REFERENCE1", "channel": "client"}' -H "Content-Type: application/json" "http://localhost:8080/v1/status"

echo ""

curl -X POST -d '{"reference": "REFERENCE4", "channel": "client"}' -H "Content-Type: application/json" "http://localhost:8080/v1/status"

echo ""

curl -X POST -d '{"reference": "REFERENCE5", "channel": "client"}' -H "Content-Type: application/json" "http://localhost:8080/v1/status"

echo ""

curl -X POST -d '{"reference": "REFERENCE1", "channel": "internal"}' -H "Content-Type: application/json" "http://localhost:8080/v1/status"

echo ""

curl -X POST -d '{"reference": "REFERENCE4", "channel": "internal"}' -H "Content-Type: application/json" "http://localhost:8080/v1/status"

echo ""

curl -X POST -d '{"reference": "REFERENCE5", "channel": "internal"}' -H "Content-Type: application/json" "http://localhost:8080/v1/status"

echo ""

