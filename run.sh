#!/bin/bash
set -e
set -x

./gradlew :distributions:demo-server:dockerize

docker run --rm -p 3000:443 -p 5005:5005 -p 8080:8080 --env-file distributions/demo-server/env.secrets --name dtp-demo datatransferproject/demo

# build the jar
#./gradlew :distributions:demo-server:build

# import environment vars
#export $(grep -v "^#" distributions/demo-server/env.secrets | grep -v "^\w$")

# run the server
#java -jar distributions/demo-server/build/libs/demo-server-all.jar
