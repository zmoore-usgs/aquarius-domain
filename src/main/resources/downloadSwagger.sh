#!/bin/bash
#CONFIGURATION
SWAGGERBASEURL="${1:-"http://tsqa.nwis.usgs.gov/AQUARIUS/Publish/v2"}"
SWAGGERROOTPATH="${2:-"/resources"}"

#Sourced from https://gist.github.com/cjus/1047794
function jsonValue() {
    KEY=$1
    num=$2
    awk -F"[,:}]" '{for(i=1;i<=NF;i++){if($i~/'$KEY'\042/){print $(i+1)}}}' | tr -d '"' | sed -n ${num}p
}

#remove old downloads
rm -rf swagger

#create download folder
mkdir -p swagger

#move to download folder
cd swagger
SWAGGEROUTDIR=$(pwd)

#download root json
curl -s $SWAGGERBASEURL$SWAGGERROOTPATH | sed 's='$SWAGGERBASEURL'='$SWAGGEROUTDIR'=g' > swagger.json

#parse root json
PATHSTRING="$(cat swagger.json | jsonValue path)"
IFS=$'\n' read -rd '' -a PATHLIST <<< "$PATHSTRING"

#download sub-json
for i in "${!PATHLIST[@]}"
do
    JSON=$SWAGGERBASEURL"${PATHLIST[i]}"
    echo Downloading $JSON

    #build folder path
    IFS=$'/' read -rd '' -a PATHPARTS <<< "${PATHLIST[i]}"

    for (( j=1; j<$(( ${#PATHPARTS[@]} -1)); j++ ))
    do
        mkdir -p "${PATHPARTS[j]}"
        cd "${PATHPARTS[j]}"
    done
    for (( j=1; j<$(( ${#PATHPARTS[@]} -1)); j++ ))
    do
        cd ..
    done

    #download json
    curl -s $JSON | sed 's='$SWAGGERBASEURL'='$SWAGGEROUTDIR'=g' > ".""${PATHLIST[i]}"
done