# under construction

## OAuth2 server and resource at same time

# start

    docker-compose up -d

or

    docker-compose -f docker-compose-with-external-data.yml up -d

from root directory:

    docker-compose -f ./oauth2-server-postgresql-example/docker-compose-with-external-data.yml up -d
    docker-compose -f ./oauth2-server-postgresql-example/docker-compose-with-external-data.yml down
    docker-compose -f ./oauth2-server-postgresql-example/docker-compose.yml up -d
    docker-compose -f ./oauth2-server-postgresql-example/docker-compose.yml down

starting server

    gradle bootRun


## check enabled | mapped endpoints:

    http://localhost:8090/actuator/mappings

