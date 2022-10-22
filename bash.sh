#!/bin/bash

echo "Start Build Docker images:"

function build_auth_service(){
	cd AuthService
	mvn clean install
	docker rmi auth-service
	docker build -t auth-service .
	cd ..
}

function build_central_admin(){
	cd CentralAdmin
	mvn clean install
	docker rmi central-admin
	docker build -t central-admin .
	cd ..
}

function build_customer(){
	cd Customer
	mvn clean install
	docker rmi customer
	docker build -t customer .
	cd ..
}

function build_swift(){
	cd Swift
	mvn clean install
	docker rmi swift
	docker build -t swift .
	cd ..
}

function build_export(){
	cd Export
	mvn clean install
	docker rmi export
	docker build -t export .
	cd ..
}

function build_discovery_service(){
	cd DiscoveryService
	mvn clean install
	docker rmi discovery-service
	docker build -t discovery-service .
	cd ..
}

function build_getway_service(){
	cd GetwayService
	mvn clean install
	docker rmi getway-service
	docker build -t getway-service .
	cd ..
}

for arg in "$@"
do
	if [ "$arg" == 'all' ]; then
		build_auth_service
		build_central_admin
		build_customer
		build_export
		build_swift
		build_getway_service
		build_discovery_service
	else 
		if [ "$arg" == 'auth-service' ]; then
		build_auth_service
		fi
		if [ "$arg" == 'central-admin' ]; then
			build_central_admin
		fi
		if [ "$arg" == 'customer' ]; then
			build_customer
		fi
		if [ "$arg" == 'export' ]; then
			build_export
		fi
		if [ "$arg" == 'swift' ]; then
			build_swift
		fi
		if [ "$arg" == 'getway-service' ]; then
			build_getway_service
		fi
		if [ "$arg" == 'discovery-service' ]; then
			build_discovery_service
		fi
	fi
	
done

echo "Build Docker Images Finished."