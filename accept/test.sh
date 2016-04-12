#/bin/bash

mvn clean test -DbaseURI=http://localhost:8080
zip -r target.zip target;