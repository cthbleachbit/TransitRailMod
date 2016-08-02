#!/usr/bin/env bash

sed -i build.gradle -e "s/^version = \".*\"/version = \"${1}\"/g"
sed -i src/main/resources/mcmod.info -e "s/\"version\": \".*\"/\"version\": \"${1}\"/g"
sed -i src/main/java/tk/cth451/transitrailmod/References.java -e "s/VERSION = \".*\";/VERSION = \"${1}\";/g"
