# panache

## Running the application in dev mode

```shell
./gradlew quarkusDev
```
press `w` to open browser


## Packaging

```shell
./gradlew build
java -jar build/quarkus-app/quarkus-run.jar
```

## Uber-jar

```shell
./gradlew build -Dquarkus.package.jar.type=uber-jar
java -jar build/*-runner.jar
```
