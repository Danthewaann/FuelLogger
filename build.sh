#!/usr/bin/env bash

# Get working directory of build script
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

# Clean the build directory
rm -rf $SCRIPT_DIR/build/*

# Compile .java files into build/
javac -Xlint:unchecked $(find $SCRIPT_DIR/src/ -name "*.java") -d $SCRIPT_DIR/build/

# Create obj/FuelLogger.jar from files in build/
echo "------------- CREATING JAR -------------"
jar cvfm $SCRIPT_DIR/obj/FuelLogger.jar $SCRIPT_DIR/META-INF/MANIFEST.MF -C $SCRIPT_DIR/build/ .
echo "----------------------------------------"