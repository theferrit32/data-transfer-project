#!/bin/bash
set -e

./gradlew :distributions:demo-server:dockerize

docker run --rm -p 3000:443 -p 5005:5005 -p 8080:8080 --env-file distributions/demo-server/env.secrets --name dtp-demo datatransferproject/demo

