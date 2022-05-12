#!/bin/bash -e

VERSION=$(mvn -q \
    -Dexec.executable="echo" \
    -Dexec.args='${project.version}' \
    --non-recursive \
    org.codehaus.mojo:exec-maven-plugin:1.3.1:exec)

if [[ $VERSION = *"SNAPSHOT"* ]]; then
  echo "Do not publish a snapshot version of tic-taac"
  exit 1
fi
docker inspect --type=image rusakovichma/tic-taac:$VERSION  > /dev/null 2>&1
if [[ "$?" -ne 0 ]] ; then
  echo "docker image rusakovichma/tic-taac:$VERSION does not exist - run build_docker.sh first"
  exit 1
fi
docker inspect --type=image rusakovichma/tic-taac:latest  > /dev/null 2>&1
if [[ "$?" -ne 0 ]] ; then
  echo "docker image rusakovichma/tic-taac:latest does not exist - run build_docker.sh first"
  exit 1
fi

docker push rusakovichma/tic-taac:$VERSION
docker push rusakovichma/tic-taac:latest