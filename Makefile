SHELL := /bin/bash

## Usage: make [target]
.PHONY : help
help : Makefile
	@sed -n 's/^##//p' $<

## [build] build the apps with all tests
run:
	mvn clean install
	docker-compose -f docker-compose.yml up -d
	mvn spring-boot:run
setup:
	. functions.sh; \
