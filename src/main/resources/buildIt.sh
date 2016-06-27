#!/bin/bash
AQ_HOST=$1

pwd
echo 'START SCHEMA DOWNLOADS'
src/main/resources/getSchemas.sh $AQ_HOST
echo 'START SCHEMA MANIPULATION'
src/main/resources/manipulateDataStructure.sh