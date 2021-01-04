<h1> VMware has ended active development of this project, this repository will no longer be updated.</h1><br># pcf-gpbroker-retailclient
Project Summary:
- Pivotal CF Client App and Service Broker Example
- Service Broker to provide Greenplum/Hawq Connection Information to the Client App
- Spring Cloud to connect the Client App to Greenplum/Hawq
- The Client App queries the product_sales_by_store View of Schema retail_demo on HAWQ in vSphere EMEA env

Project Goal:
- Show how to extend Spring Cloud and to develop a sercice broker to connect apps on PCF to existing data sources

Project Technologies:
- AngularJS, Bootstrap, 
- Spring Boot, Spring Rest Repository, Spring Data, Spring Cloud

[greenplum-service-broker](/greenplum-service-broker):
- Based on [spring-boot-cf-service-broker](https://github.com/cloudfoundry-community/spring-boot-cf-service-broker)
- Provides a retail-demo service plan 
- Provides Credentials to a specific db and schema on HAWQ in the EMEA FE vSphere env

[retaildata-client](/retaildata-client): 
- Consumes a Greenplum/HAWQ service external to PCF
- Provides a client app for queries and visualization of the product_sales_by_store view

![Alt text](/docs/app-manager.png?raw=true "app-manager")

![Alt text](/docs/retaildata-client.png?raw=true "retaildata-client")

Options to run the project:
- You need to be on the Pivotal VPN (HAWQ is in the EMEA vSphere env)
- You can run the client on your local machine or on PCF

Prerequistites
- maven and a JDK on your machine
- Download the HAWQ driver from [network.pivotal.io](https://network.pivotal.io/products/pivotal-hawq#/releases/252/file_groups/230)
- install the driver in your local maven repository
```
mvn install:install-file -Dfile=./retaildata-client/retaildata-connector/src/main/resources/lib/greenplum.jar -DgroupId=pivotal.greenplum -DartifactId=io.pivotal -Dversion=5.1.1 -Dpackaging=jar
```

# Quick start
- Target cf api with your cf cli 
- cd into greenplum-service-broker
- execute: ./gradlew assemble
- execute: cf push (if the broker is already on your pcf instance, change the broker name/id in the code and change the app name)
- Execute: cf create-service-broker greenplum user password http://route
- Execute: cf enable-service-access greenplum -o org
- Execute: cf create-service  greenplum retail-demo mydb
- cd into retaildata-client
- execute: mvn package
- change the manifest to what is required with your setup
- exceute: cf push
- Point your browser to the retaildata-client route

# How to consume the service in own java applications
The retaildata-client project contains two sub projects (retaildata-connector and retaildata-ui). The retaildata-connector sub project builds the 'retaildata-connector.jar' required in retaildata-ui and also in your own application.

The retaildata-ui project uses the retaildata-connector dependency like this in the maven [pom.xml](/retaildata-client/retaildata-ui/pom.xml)

		<dependency>
			<groupId>pivotal.retaildata</groupId>
			<artifactId>retaildata-connector</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>

The connection string used by the service broker is defined in file [application.properties](/greenplum-service-broker/src/main/resources/application.properties) like this
```
retail_demo_db_url=greenplum://gpadmin:gpadmin@10.68.58.123:5432/test
```
If you change this property the service broker will provide your connection string. The retaildata-connector in your application will automatically create a DataSource (connected to Greenplum or HAWQ) without the need to deal with any cloud stuff.
