## OAuth2 server and resource at same time

# start

    docker-compose up -d

or

    docker-compose -f docker-compose-with-external-data.yml up -d

    gradle bootRun


## check enabled | mapped endpoints:

    http://localhost:8090/actuator/mappings

