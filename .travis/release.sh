#!/usr/bin/env bash

set -e

echo "Change pom version to $TRAVIS_TAG"
mvn --batch-mode versions:set -DnewVersion=${TRAVIS_TAG}

java --version

echo "Uploading to oss repo and GitHub"
mvn -s .travis/settings.xml --batch-mode clean deploy -P release -DskipTests