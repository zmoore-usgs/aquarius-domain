#!/bin/bash
XSD_DIRECTORY=target/generated-sources/aqSchema

pwd
mkdir -p $XSD_DIRECTORY

COUNTER=0
while [ `curl -s -o /dev/null -w "%{http_code}" "http://testqa.owicloud.org/AQUARIUS/Publish/v2/metadata?xsd=$COUNTER"` -eq 200 ]; do
	wget "http://testqa.owicloud.org/AQUARIUS/Publish/v2/metadata?xsd=$COUNTER" -O$XSD_DIRECTORY/$COUNTER.xsd
	let COUNTER=COUNTER+1
done