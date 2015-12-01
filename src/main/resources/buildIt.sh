#!/bin/bash
pwd
echo 'START SCHEMA DOWNLOADS'
src/main/resources/getSchemas.sh
echo 'START SCHEMA MANIPULATION'
src/main/resources/manipulateDataStructure.sh