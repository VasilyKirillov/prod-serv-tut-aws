#!/bin/bash

docker run -d --name=pg -p5432:5432 \
 -e POSTGRES_USER=admin \
 -e POSTGRES_PASSWORD=1qa@WS3ed \
 -e POSTGRES_DB=aws_tutor \
 -e PGDATA=/pgdata \
 -v ~/pgdata:/pgdata \
 postgres:13.1-alpine