# example using spring r2dbc and azure mssql

## Requirements

- Azure SQL Server, Azure SQL Database
- Azure App Service to deploy docker container to.  S1-1 was used in testing.

- Java 8 - to compile spring boot app.

- You can see the environment properties that need to be set in template.env.

- Failure only happens when deployed to Azure app service.  S1-1 level was in testing. 

- JMeter test file is simpleTest.jmx

---
## Building Docker Container
 
```docker build -t com-example-post-service-spring-r2dbc-mssql .```

---
## Execute locally

- Copy template.env as .env.
- Set properties in .env file. SQL_MAX in testing tried 10,25,50,100,200 all had failures when deployed to azure no failures local.
```docker run -p 8080:8080 -i -t --env-file ./.env com-example-post-service-spring-r2dbc-mssql:latest```

run JMeter -- execute simpleTest.jmx -- By default this is set to localhost and port 8080.

Again no failure is experienced here. 

## Deploy to Azure app services.

- Deploy your container to your appservice
- Set your container settings. Same as properties above. And you might want to turn on log tracing/analytics. 
- Start up service.
- Edit JMeter test to point to your app service endpoint.  Blank out 8080 port. https instead of http.
- Run test.  You should see some failures. Not all will fail.  

