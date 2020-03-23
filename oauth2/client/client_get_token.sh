#!/bin/sh
#TOKEN=`curl -s -u user:user -X POST localhost:8090/oauth/token\?grant_type=client_credentials | egrep -o '[a-f0-9-]{20,}'`
TOKEN=`curl -s -u guest_app:secret -X POST localhost:8090/oauth/token\?grant_type=client_credentials | egrep -o '[a-f0-9-]{20,}'`
echo "Got token for curl client as : $TOKEN"
