======================
Readme
======================

bootstrap
======================

#./gradlew tasks

This will automatically down all gradle dependencies


create intellij idea project structure
==============================================

#./gradlew idea

This will create a intellij idea project structure

build and run
==============================================

#./gradlew build && java -jar build/libs/spring-seed-0.1.0.jar

build rpm
==============================================
#./gradlew buildRpm


tests
============

unit tests
-----------------

#./gradlew ut

integratin tests
----------------------

#./gradlew it


acceptance tests
----------------------

#./gradlew at

test in browser
-------------------

http://localhost:8080/hotels/1

publish
================

This will publish the rpm to nexus repo

#./gradlew publish

local run one line command
======================================

./gradlew clean buildRpm ut it at && java -jar build/libs/springseed-1.devbuild.jar

