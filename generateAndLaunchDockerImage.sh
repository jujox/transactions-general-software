#!/bin/bash

action=$1

if [ -z ${action} ]; then
    echo -e "Syntax: $0 <generate|launch>"
    exit
fi

if [ ${action} == "generate" ]; then
    # This should generate a jar file named target/transactions-0.0.1-SNAPSHOT.jar
    mvn clean package

    # This will generate a simple docker image
    if [ -f target/transactions-0.0.1-SNAPSHOT.jar ]; then
        docker build -t transactions:0.0.1-SNAPSHOT .
    fi
elif [ ${action} == "launch" ]; then
    docker run -d --name "codetest_transactions_image" --rm  -p 8080:8080 transactions:0.0.1-SNAPSHOT
    echo "Image launched. Try this curl.sh to do some requests"
    echo "To stop and remove image: docker stop codetest_transactions_image"
else
    echo "Syntax: $0 <generate|launch>"
fi

