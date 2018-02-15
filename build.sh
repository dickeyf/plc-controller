#!/usr/bin/env bash
gradle assemble

cd web-client
au build --env prod
cp index.html ../public/
cp -rf scripts ../public/