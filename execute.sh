#!/bin/bash
set -e

case "$1" in
  build)
    ./gradlew bootJar
    ;;
  up)
    ./gradlew bootRun
    ;;
  rebuild)
    ./gradlew clean bootRun
    ;;
  clean)
    ./gradlew clean
    ;;
  *)
    echo "Usage: ./execute.sh {build|up|rebuild|clean}"
    ;;
esac
