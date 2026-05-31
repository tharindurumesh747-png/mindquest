#!/usr/bin/env sh
set -e
GRADLE_VERSION=8.4
GRADLE_HOME=$HOME/.gradle/wrapper/dists/gradle-$GRADLE_VERSION-bin
mkdir -p $GRADLE_HOME
if [ ! -f "$GRADLE_HOME/gradle-$GRADLE_VERSION/bin/gradle" ]; then
  curl -L https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip -o $GRADLE_HOME/gradle.zip
  unzip -q $GRADLE_HOME/gradle.zip -d $GRADLE_HOME
fi
$GRADLE_HOME/gradle-$GRADLE_VERSION/bin/gradle "$@"
