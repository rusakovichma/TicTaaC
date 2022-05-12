#!/bin/bash -e

VERSION=$(mvn -q \
    -Dexec.executable="echo" \
    -Dexec.args='${project.version}' \
    --non-recursive \
    org.codehaus.mojo:exec-maven-plugin:1.3.1:exec)

FILE=./target/tic-taac-$VERSION-release.zip
if [ -f "$FILE" ]; then
    docker build . --build-arg VERSION=$VERSION -t rusakovichma/tic-taac:$VERSION
    if [[ ! $VERSION = *"SNAPSHOT"* ]]; then
        docker tag rusakovichma/tic-taac:$VERSION rusakovichma/tic-taac:latest
    fi
else
    echo "$FILE does not exist - run 'mvn package' first"
    exit 1
fi