#!/usr/bin/env bash

set -e

echo "[DEPLOY] =========== Change pom version to $TRAVIS_TAG"
mvn --batch-mode versions:set -DnewVersion=${TRAVIS_TAG}

echo "[DEPLOY] =========== Uploading to UM repo"
mvn -s .travis/settings.xml --batch-mode -P um-deploy clean deploy -DskipTests || true

echo "[DEPLOY] =========== Uploading to oss.sonatype.org"
mvn -s .travis/settings.xml --batch-mode -P central-deploy clean deploy -DskipTests



