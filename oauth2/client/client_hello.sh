#!/bin/sh
#TOKEN=`curl -s -u user:user -X POST localhost:8090/oauth/token\?grant_type=client_credentials | egrep -o '[a-f0-9-]{20,}'`

curl localhost:8090/hello/me
echo

curl localhost:8090/hello/authorized
echo


####      'guest_app' verification

TOKEN=`curl -s -u guest_app:secret -X POST localhost:8090/oauth/token\?grant_type=client_credentials | egrep -o '[a-f0-9-]{20,}'`
echo "Got token for curl client as : $TOKEN"

curl localhost:8090/hello/me -H "Authorization: Bearer $TOKEN"
echo

curl localhost:8090/hello/authorized -H "Authorization: Bearer $TOKEN"
echo

curl localhost:8090/hello/authorized-read -H "Authorization: Bearer $TOKEN"
echo



#######################################################################################################################
####
####      'user' verification. client with 'user' name is absent in the 'oauth_client_details' table.
####      so neither authentication nor authorization
####
#######################################################################################################################

TOKEN=`curl -s -u user:user -X POST localhost:8090/oauth/token\?grant_type=client_credentials | egrep -o '[a-f0-9-]{20,}'`
echo "Got token for curl client as : $TOKEN"

curl localhost:8090/hello/me -H "Authorization: Bearer $TOKEN"
echo

curl localhost:8090/hello/authorized -H "Authorization: Bearer $TOKEN"
echo

curl localhost:8090/hello/authorized-read -H "Authorization: Bearer $TOKEN"
echo
