# transactions

This is an SpringBoot 2.7.5 + Maven based project.

To build it:

```
mvn clean package
```

To run it in local:
```
./mvnw spring-boot:run
```

A bash script is provided to build it, create a docker image and run it.

Generate docker image:
```
./generateAndLaunchDockerImage.sh generate
```

Launch docker image (port 8080):
```
./generateAndLaunchDockerImage.sh launch
```

