# Microservices oktatás

```
docker run -p 8080:8080 --name my-employees-ventocom training360/employees-ventocom
```

```
docker build -t employees .
docker run --name my-employees -p 8080:8080 employees
```

```
java -jar employees-0.0.1-SNAPSHOT.jar 
java -Demployees.message=HelloCommandLine -jar employees-0.0.1-SNAPSHOT.jar 
set EMPLOYEES_MESSAGE=HelloEnvironmentVariable
docker run -p 8080:8080 --name my-employees -eEMPLOYEES_MESSAGE=HelloFromDocker employees

docker run --name employees-postgres -e POSTGRES_PASSWORD=password -d -p 5432:5432 postgres
```